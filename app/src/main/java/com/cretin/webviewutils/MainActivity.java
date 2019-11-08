package com.cretin.webviewutils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.util.LinkifyCompat;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cretin.tools.openwebview.OpenWebActivity;
import com.cretin.tools.openwebview.WebUtilsConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.ed_url);

        findViewById(R.id.tv_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                OpenWebActivity.openWebView(MainActivity.this, url);
            }
        });

        findViewById(R.id.tv_open_config).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                WebUtilsConfig config =
                        new WebUtilsConfig()
                                .setTitleBackgroundColor(R.color.colorAccent)//设置标题栏背景色
                                .setBackText("关闭")//设置返回按钮的文案
                                .setBackBtnRes(R.mipmap.arrow_left_white)//设置返回按钮的图标
                                .setMoreBtnRes(R.mipmap.more_web)//设置更多按钮的图标
                                .setShowBackText(true)//设置是否显示返回按钮的文案
                                .setShowMoreBtn(true)//设置是否显示更多按钮
                                .setShowTitleLine(false)//设置是否显示标题下面的分割线
                                .setShowTitleView(true)//设置是否显示标题栏，网页是全屏的时候可以选择隐藏标题栏
                                .setTitleBackgroundRes(-1)//设置标题栏背景资源
                                .setTitleLineColor(R.color.app_title_color);//设置标题栏下面的分割线的颜色
                OpenWebActivity.openWebView(MainActivity.this, url, config);
            }
        });

        TextView tvInfo = findViewById(R.id.tv_info);
        final SpannableString value = SpannableString.valueOf(tvInfo.getText());
        LinkifyCompat.addLinks(value, Linkify.ALL);
        tvInfo.setMovementMethod(LinkMovementMethod.getInstance());
        tvInfo.setText(value);
    }
}
