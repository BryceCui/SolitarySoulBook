package com.cuipengyu.solitarysoulbook.mvp.model;

import android.support.annotation.MainThread;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsBean;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.entity.httphelper.DefaultObserver;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.mvp.controller.BookDeTailsActivityController;

import io.reactivex.Scheduler;
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
public class BookDeTailsModel implements BookDeTailsActivityController.BookDeTailsModel {
    @Override
    public void getBook(String bookName, final BaseHttpEntity<BookDetailsBean> httpEntity) {
   RetrofitHelper.getService().getApi().getSearchBookPackage(bookName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<BookDetailsBean>() {
       @Override
       public void onSubscribe(Disposable d) {

       }

       @Override
       public void onSuccess(BookDetailsBean bookDetailsBean) {
         httpEntity.onSuccess(bookDetailsBean);
       }

       @Override
       public void onError(Throwable e) {

       }
   });

    }
}
