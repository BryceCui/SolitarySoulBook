package com.cuipengyu.solitarysoulbook.entity.httphelper;

import com.cuipengyu.solitarysoulbook.entity.bean.BaseBean;

import java.util.Map;

public interface HttpEngine {

    <T extends BaseBean> void getServiceJson(String url,Class<T> tClass, CallBack<T > callBack);

    HttpEngine param(String key, String value);

    HttpEngine param(Map<String, String> map);

    interface CallBack<T extends BaseBean> {
        //请求成功
        void onSuccess(T  t);

        //请求失败
        void onError(String errMsg);

        // !isOk
        void onFailure(String result);
    }
}
