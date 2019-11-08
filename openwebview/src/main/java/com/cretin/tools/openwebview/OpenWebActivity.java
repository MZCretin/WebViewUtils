package com.cretin.tools.openwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cretin.tools.openwebview.utils.TopPopWindow;
import com.cretin.tools.openwebview.utils.WebCommonUtils;
import com.cretin.tools.openwebview.utils.WedgetUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;

import java.util.Map;

public class OpenWebActivity extends AppCompatActivity {

    private AgentWeb mAgentWeb;
    public static final String TAG = "OpenWebActivity";
    public static final String TAG_SPLIT = "OpenWebActivity_Split";

    private boolean isKitkat = false;

    private TextView mTitleTextView;
    private TextView mTitleBack;
    private ImageView ivMore;
    private LinearLayout llWebTitle;
    private View titleLine;

    //当前的链接
    private String currentUrl;

    private WebUtilsConfig webConfig;

    /**
     * 打开页面
     *
     * @param context
     * @param url
     */
    public static void openWebView(Context context, String url) {
        Intent intent = new Intent(context, OpenWebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    /**
     * 打开页面
     *
     * @param context
     */
    public static void openWebView(Context context, String url, WebUtilsConfig config) {
        openWebView(context, url, config, null);
    }

    /**
     * 打开页面
     *
     * @param context
     */
    public static void openWebView(Context context, String url, Map<String, String> cookies) {
        openWebView(context, url, null, cookies);
    }

    /**
     * 打开页面 带配置
     *
     * @param context
     * @param url
     */
    public static void openWebView(Context context, String url, WebUtilsConfig config, Map<String, String> cookies) {
        StringBuffer cookieStr = new StringBuffer();
        if (cookies != null) {
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                cookieStr.append(entry.getKey() + TAG_SPLIT + entry.getValue() + TAG);
            }
        }
        Intent intent = new Intent(context, OpenWebActivity.class);
        intent.putExtra("url", url);
        if (config != null)
            intent.putExtra("config", config);
        if (cookieStr.length() != 0) {
            intent.putExtra("cookie", cookieStr.substring(0, cookieStr.length() - TAG.length()));
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() == null || TextUtils.isEmpty(getIntent().getStringExtra("url"))) {
            Toast.makeText(this, getResources().getString(R.string.str_web_url_null), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currentUrl = getIntent().getStringExtra("url");
        webConfig = getIntent().getParcelableExtra("config");
        if (webConfig == null) {
            webConfig = WebCommonUtils.getDefaultConfig(this);
        }

        //清除所有cookies
        AgentWebConfig.removeExpiredCookies();
        AgentWebConfig.removeSessionCookies();
        AgentWebConfig.removeAllCookies();

        //获取cookie
        String cookie = getIntent().getStringExtra("cookie");
        if (!TextUtils.isEmpty(cookie)) {
            String[] cookieTemp = cookie.split(TAG);
            for (String singleCookie : cookieTemp) {
                AgentWebConfig.syncCookie(singleCookie.split(TAG_SPLIT)[0], singleCookie.split(TAG_SPLIT)[1]);
            }
        }

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            isKitkat = true;
        }
        setContentView(R.layout.activity_open_web);

        findView();

        addListener();

        if (isKitkat) {
            llWebTitle.setPadding(0, WedgetUtils.getStatusBarHeight(this), 0, 0);
        }

        doConfig();

        //        AgentWeb.with(this)
//                .setAgentWebParent((LinearLayout) view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
//                .useDefaultIndicator(-1, 3)
//                .setAgentWebWebSettings(getSettings())
//                .setWebViewClient(mWebViewClient)
//                .setWebChromeClient(mWebChromeClient)
//                .setPermissionInterceptor(mPermissionInterceptor)
//                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//                .setAgentWebUIController(new UIController(getActivity()))
//                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
//                .useMiddlewareWebChrome(getMiddlewareWebChrome())
//                .useMiddlewareWebClient(getMiddlewareWebClient())
//                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
//                .interceptUnkownUrl()
//                .createAgentWeb()
//                .ready()
//                .go(getUrl());


        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) findViewById(R.id.ll_web_root), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()
                .ready()
                .go(currentUrl);


    }

    private void addListener() {
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopPopWindow topPopWindow = new TopPopWindow(OpenWebActivity.this, new TopPopWindow.OnTopItemClickListener() {
                    @Override
                    public void onItemClick(View v) {
                        if (v.getId() == R.id.tv_01) {
                            WebCommonUtils.copy(currentUrl, OpenWebActivity.this);
                            Toast.makeText(OpenWebActivity.this, R.string.str_web_copy, Toast.LENGTH_SHORT).show();
                        } else if (v.getId() == R.id.tv_02) {
                            if (mAgentWeb != null) {
                                mAgentWeb.getUrlLoader().reload();
                            }
                        } else if (v.getId() == R.id.tv_03) {

                        }
                    }
                });
                //监听窗口的焦点事件，点击窗口外面则取消显示
                topPopWindow.showAsDropDownView(view);
            }
        });

        mTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findView() {
        mTitleTextView = findViewById(R.id.tv_title_info);
        mTitleBack = findViewById(R.id.tv_back);
        ivMore = findViewById(R.id.iv_right);
        llWebTitle = findViewById(R.id.ll_web_title);
        titleLine = findViewById(R.id.line_divider);
    }

    private com.just.agentweb.WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mTitleTextView != null) {
                mTitleTextView.setText(title);
            }
        }
    };

    private com.just.agentweb.WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            currentUrl = url;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    //设置配置文件内容
    private void doConfig() {
        if (webConfig.isShowBackText())
            mTitleBack.setText("~".equals(webConfig.getBackText()) ? getResources().getString(R.string.str_web_back) : (webConfig.getBackText()));
        else {
            mTitleBack.setText("");
        }
        if (webConfig.getTitleBackgroundRes() != -1) {
            llWebTitle.setBackgroundResource(webConfig.getTitleBackgroundRes());
        }
        if (webConfig.getTitleBackgroundColor() != -1) {
            llWebTitle.setBackgroundColor(getResources().getColor(webConfig.getTitleBackgroundColor()));
        }
        if (webConfig.getBackBtnRes() != -1)
            mTitleBack.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(webConfig.getBackBtnRes()), null, null, null);
        if (webConfig.getMoreBtnRes() != -1)
            ivMore.setImageResource(webConfig.getMoreBtnRes());
        if (webConfig.isShowMoreBtn()) {
            ivMore.setVisibility(View.VISIBLE);
        } else {
            ivMore.setVisibility(View.GONE);
        }
        if (webConfig.isShowTitleLine()) {
            titleLine.setVisibility(View.VISIBLE);
        } else {
            titleLine.setVisibility(View.GONE);
        }
        if (webConfig.getTitleLineColor() != -1)
            titleLine.setBackgroundColor(webConfig.getTitleLineColor());
        if (webConfig.isShowTitleView()) {
            llWebTitle.setVisibility(View.VISIBLE);
        } else {
            llWebTitle.setVisibility(View.GONE);
        }
    }
}
