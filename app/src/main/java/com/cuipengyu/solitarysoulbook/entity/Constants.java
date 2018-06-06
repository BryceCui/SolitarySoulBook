package com.cuipengyu.solitarysoulbook.entity;

import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.cuipengyu.solitarysoulbook.utils.FileUtil;

public class Constants {

    //判断sd卡是否存在并且返回包名下文件路径
    public static String PATH_DATA = FileUtil.createRootPath(ApplicationContextUtil.getAppConnect()) + "/cache";
    public static String PATH_TXT = PATH_DATA + "/book/";
    public static final String ISNIGHT = "isNight";

    /**
     * 精选模块
     * POPULAR_WEEK本周热门
     * PUBLISHING出版
     * MALE男生
     * GIRL女生
     * NEW_BOOK新书
     * FINISHED_BOOK完本
     */
    public static final String PAGE="?page=";
    public static final String POPULAR_WEEK = "bzrt";
    public static final String PUBLISHING = "cxjp";
    public static final String MALE = "nsjx";
    public static final String GIRL = "vsjx";
    public static final String MALE_NEW_BOOK = "npxsqx";
    public static final String GIRL_NEW_BOOK = "vpqlxs";
    public static final String MALE_FINISHED_BOOK = "npwblz";
    public static final String GIRL_FINISHED_BOOK = "vpwblz";
    /**
     * base url
     */
    public static final String Api_baseUrl = "http://api.zhuishushenqi.com/";
    public static final String http_baseUrl = "http://www.zhuishushenqi.com/";
    public static final String IMG_BASE_URL = "http://statics.zhuishushenqi.com";
    public static String USER_NAME="";
}
