package com.cuipengyu.solitarysoulbook.mvp.model;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.httphelper.DefaultObserver;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.mvp.controller.SearchActivityController;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchActivityModel implements SearchActivityController.searchModel {

    @Override
    public void getHotWord(final BaseHttpEntity<HotWord> httpEntity) {
        RetrofitHelper.getService().getApi().getHotWord().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new DefaultObserver<HotWord>() {
            @Override
            public void onError(String error) {
                httpEntity.onError(error);
            }

            @Override
            public void onSuccess(HotWord data) {
                httpEntity.onSuccess(data);

            }

            @Override
            public void onFinish() {
                httpEntity.onFinish();
            }
        });
    }
}
