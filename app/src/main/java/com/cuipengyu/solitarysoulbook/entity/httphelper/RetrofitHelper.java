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
    private static String BASE_URL = "";

    private RetrofitHelper() {
    }
    public static RetrofitHelper getService() {
        if (mHelper == null)   mHelper = new RetrofitHelper();
        BASE_URL = Constants.Api_baseUrl;
        return mHelper;
    }

    public static RetrofitHelper getService(String index) {
        if (mHelper == null) mHelper = new RetrofitHelper();
        BASE_URL = index;
        return mHelper;
    }

    public RetrofitService getApi() {
        return RetrofitApi.getApiService(RetrofitService.class, BASE_URL);
    }
}



