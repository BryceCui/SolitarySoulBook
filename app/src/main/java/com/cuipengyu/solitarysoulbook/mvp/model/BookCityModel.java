package com.cuipengyu.solitarysoulbook.mvp.model;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityRecommendBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCitySpread;
import com.cuipengyu.solitarysoulbook.entity.httphelper.DefaultObserver;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.mvp.controller.BookCityController;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
        RetrofitHelper.getService(Constants.http_baseUrl).getApi().getSpread().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<BookCitySpread>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(BookCitySpread bookCitySpread) {
                entity.onSuccess(bookCitySpread);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    public void getBookListRecommend(final BaseHttpEntity<BookCityRecommendBean> entity) {
        RetrofitHelper.getService().getApi().getRecommendBookPackage().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<BookCityRecommendBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(BookCityRecommendBean bookCityRecommendBean) {
                entity.onSuccess(bookCityRecommendBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
