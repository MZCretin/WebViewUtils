package com.cretin.tools.openwebview.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * @date: on 2019-11-07
 * @author: a112233
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class WedgetUtils {
    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
