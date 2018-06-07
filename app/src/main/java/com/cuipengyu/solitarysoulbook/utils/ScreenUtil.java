package com.cuipengyu.solitarysoulbook.utils;

/**
 * 屏幕的工具类
 * Created by cuipengyu on 2018/3/14.
 */

public class ScreenUtil {

    /**
     * 获取屏幕宽
     */
    public static int getAppWidth() {
        return ApplicationContextUtil.getAppConnect().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高
     */
    public static int getAppHeight() {
        return ApplicationContextUtil.getAppConnect().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 从 dp(像素) 的单位 转成为 px
     */
    public static int dpToPx(float dp) {
        float density = ApplicationContextUtil.getAppConnect().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }

    /**
     * 从 px(像素) 的单位 转成为 dp
     */
    public static int pxToDp(float px) {
        final float scale = ApplicationContextUtil.getAppConnect().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    public static int dpToPxInt(float dp) {
        return (int) (dpToPx(dp) + 0.5f);
    }

}
