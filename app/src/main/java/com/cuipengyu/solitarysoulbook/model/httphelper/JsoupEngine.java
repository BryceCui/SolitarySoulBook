package com.cuipengyu.solitarysoulbook.model.httphelper;

import com.cuipengyu.solitarysoulbook.base.BaseBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/11
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public interface JsoupEngine {
    <T extends BaseBean> void getHtmlStringData(Class<T> bean,String select,int index, CallBack<T> callBack);

    interface CallBack<T extends BaseBean> {
        void onSucces(T t);

        void onError(String error);
    }
}
