package com.cuipengyu.solitarysoulbook.entity.httphelper;

import com.cuipengyu.solitarysoulbook.entity.Constants;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class RetrofitHelper {
    private static RetrofitHelper mHelper;
    private String BASE_URL = "";

    private RetrofitHelper(int dex) {
        switch (dex) {
            case 0:
                BASE_URL = Constants.http_baseUrl;
                break;
            default:
                BASE_URL = Constants.Api_baseUrl;
                break;
        }
    }

    private RetrofitHelper() {
        BASE_URL = Constants.Api_baseUrl;
    }
    public static RetrofitHelper getService() {
        if (mHelper == null) mHelper = new RetrofitHelper();
        return mHelper;
    }

    public static RetrofitHelper getService(int index) {
        if (mHelper == null) mHelper = new RetrofitHelper(index);
        return mHelper;
    }

    public RetrofitService getApi() {
        return RetrofitApi.getApiService(RetrofitService.class, BASE_URL);
    }
}



