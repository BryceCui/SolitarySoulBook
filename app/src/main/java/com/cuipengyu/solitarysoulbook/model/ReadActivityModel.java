package com.cuipengyu.solitarysoulbook.model;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.httphelper.DefaultObserver;
import com.cuipengyu.solitarysoulbook.controller.ReadActivityController;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/24
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class ReadActivityModel implements ReadActivityController.ReadModel {
    /**
     * 小说章节获取
     *
     * @param bookid
     * @param entity
     */
    @Override
    public void getChapterListData(String bookid, final BaseHttpEntity<ChapterLink>  entity) {
        RetrofitHelper.getService().getApi().getChapterList(bookid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<ChapterLink>() {
            @Override
            public void onSuccess(ChapterLink data) {
                entity.onSuccess(data);
            }

            @Override
            public void onError(String error) {
                entity.onError(error);
            }

            @Override
            public void onFinish() {
                entity.onFinish();
            }
        });
    }

    @Override
    public void getChapterBody(String chapterLink, BaseHttpEntity entity) {

    }
}
