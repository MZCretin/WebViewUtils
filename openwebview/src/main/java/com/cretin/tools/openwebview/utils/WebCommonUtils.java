package com.cretin.tools.openwebview.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.TypedValue;

import com.cretin.tools.openwebview.R;
import com.cretin.tools.openwebview.WebUtilsConfig;

public class WebCommonUtils {
    /**
     * dp 转 px
     * @param dp
     * @param context
     * @return
     */
    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * 复制内容到剪切板
     *
     * @param copyStr
     * @return
     */
    public static boolean copy(String copyStr, Context context) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取默认的配置
     * @return
     */
    public static WebUtilsConfig getDefaultConfig(Context context){
        return new WebUtilsConfig()
                .setBackText(context.getResources().getString(R.string.str_web_back))
                .setBackBtnRes(R.mipmap.arrow_left_white)
                .setMoreBtnRes(R.mipmap.more_web)
                .setShowBackText(true)
                .setShowMoreBtn(true)
                .setShowTitleLine(false)
                .setShowTitleView(true)
                .setTitleBackgroundColor(R.color.app_theme_color)
                .setTitleBackgroundRes(-1)
                .setTitleLineColor(R.color.white);
    }
}
