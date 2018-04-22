package com.cuipengyu.solitarysoulbook.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.cuipengyu.solitarysoulbook.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 阅读背景
 */
public class ThemeManager {

    public static final int NORMAL = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;
    public static final int LEATHER = 3;
    public static final int GRAY = 4;
    public static final int NIGHT = 5;

    public static void setReaderTheme(int theme, View view) {
        switch (theme) {
            case NORMAL:
                view.setBackgroundResource(R.drawable.theme_white_bg);
                break;
            case YELLOW:
                view.setBackgroundResource(R.drawable.theme_yellow_bg);
                break;
            case GREEN:
                view.setBackgroundResource(R.drawable.theme_green_bg);
                break;
            case LEATHER:
                view.setBackgroundResource(R.drawable.theme_leather_bg);
                break;
            case GRAY:
                view.setBackgroundResource(R.drawable.theme_gray_bg);
                break;
            case NIGHT:
                view.setBackgroundResource(R.drawable.theme_night_bg);
                break;
            default:
                break;
        }
    }

    public static Bitmap getThemeDrawable(int theme) {
        Bitmap bmp = Bitmap.createBitmap(ScreenUtil.getAppWidth(), ScreenUtil.getAppHeight(), Bitmap.Config.ARGB_8888);
        switch (theme) {
            case NORMAL:
                bmp.eraseColor(ContextCompat.getColor(ApplicationContextUtil.getAppConnect(), R.color.read_theme_white));
                break;
            case YELLOW:
                bmp.eraseColor(ContextCompat.getColor(ApplicationContextUtil.getAppConnect(), R.color.read_theme_yellow));
                break;
            case GREEN:
                bmp.eraseColor(ContextCompat.getColor(ApplicationContextUtil.getAppConnect(), R.color.read_theme_green));
                break;
            case LEATHER:
                bmp = BitmapFactory.decodeResource(ApplicationContextUtil.getAppConnect().getResources(), R.drawable.theme_leather_bg);
                break;
            case GRAY:
                bmp.eraseColor(ContextCompat.getColor(ApplicationContextUtil.getAppConnect(), R.color.read_theme_gray));
                break;
            case NIGHT:
                bmp.eraseColor(ContextCompat.getColor(ApplicationContextUtil.getAppConnect(), R.color.read_theme_night));
                break;
            default:
                break;
        }
        return bmp;
    }

//    public static List<ReadTheme> getReaderThemeData(int curTheme) {
//        int[] themes = {NORMAL, YELLOW, GREEN, LEATHER, GRAY, NIGHT};
//        List<ReadTheme> list = new ArrayList<>();
//        ReadTheme theme;
//        for (int i = 0; i < themes.length; i++) {
//            theme = new ReadTheme();
//            theme.theme = themes[i];
//            list.add(theme);
//        }
//        return list;
//    }
}
