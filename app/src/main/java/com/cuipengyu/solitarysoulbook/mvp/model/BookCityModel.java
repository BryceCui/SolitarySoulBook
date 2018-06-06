package com.cuipengyu.solitarysoulbook.mvp.model;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityRecommendBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCitySpread;
import com.cuipengyu.solitarysoulbook.entity.httphelper.DefaultObserver;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.mvp.controller.BookCityController;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityModel implements BookCityController.BookCityModel {

    @Override
    public void getBookListSpread(final BaseHttpEntity<BookCitySpread> entity) {
        RetrofitHelper.getService(0).getApi().getSpread().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BookCitySpread>() {
            @Override
            public void onError(String error) {
                entity.onError(error);
            }

            @Override
            public void onSuccess(BookCitySpread data) {
                entity.onSuccess(data);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void getBookListRecommend(final BaseHttpEntity<BookCityRecommendBean> entity) {
        RetrofitHelper.getService().getApi().getRecommendBookPackage().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<BookCityRecommendBean>() {
            @Override
            public void onError(String error) {
                entity.onError(error);
            }

            @Override
            public void onSuccess(BookCityRecommendBean data) {
                entity.onSuccess(data);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
