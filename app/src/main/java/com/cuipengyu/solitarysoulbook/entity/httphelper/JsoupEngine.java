package com.cuipengyu.solitarysoulbook.entity.httphelper;

import com.cuipengyu.solitarysoulbook.entity.bean.SelectBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/11
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public interface JsoupEngine {
    void getHtmlStringData(String select,int index, CallBack<SelectBean> callBack);

    interface CallBack<SelectBean> {
        void onSucces(com.cuipengyu.solitarysoulbook.entity.bean.SelectBean t);

        void onError(String error);
    }
}
