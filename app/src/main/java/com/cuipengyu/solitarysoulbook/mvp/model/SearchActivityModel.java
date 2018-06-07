package com.cuipengyu.solitarysoulbook.mvp.model;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.httphelper.DefaultObserver;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.mvp.controller.SearchActivityController;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
        RetrofitHelper.getService().getApi().getHotWord().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new SingleObserver<HotWord>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(HotWord hotWord) {
                httpEntity.onSuccess(hotWord);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void getAutoMaticSearch(String query, final BaseHttpEntity<AutomaticBean> httpEntity) {
        RetrofitHelper.getService().getApi().getAutoMaticSearch(query).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new SingleObserver<AutomaticBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(AutomaticBean automaticBean) {
                httpEntity.onSuccess(automaticBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
