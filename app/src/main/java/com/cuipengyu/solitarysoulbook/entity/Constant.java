package com.cuipengyu.solitarysoulbook.entity;

import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.cuipengyu.solitarysoulbook.utils.FileUtil;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class Constant {
    //判断sd卡是否存在并且返回包名下文件路径
    public static String PATH_DATA = FileUtil.createRootPath(ApplicationContextUtil.getAppConnect()) + "/cache";
    public static String PATH_TXT = PATH_DATA + "/book/";
    public static final String ISNIGHT = "isNight";
}
