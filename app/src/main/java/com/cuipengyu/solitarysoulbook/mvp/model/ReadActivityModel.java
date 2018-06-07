package com.cuipengyu.solitarysoulbook.mvp.model;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterBody;
import com.cuipengyu.solitarysoulbook.entity.httphelper.DefaultObserver;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitBuilder;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitService;
import com.cuipengyu.solitarysoulbook.mvp.controller.ReadActivityController;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.cuipengyu.solitarysoulbook.entity.Constants.IMG_BASE_CHAPTER_URL;

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
    public void getChapterListData(String bookid, final BaseHttpEntity<ChapterLink> entity) {
        RetrofitHelper.getService().getApi().getChapterList(bookid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ChapterLink>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ChapterLink chapterLink) {
                entity.onSuccess(chapterLink);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void getChapterBody(String chapterLink, final BaseHttpEntity<ChapterBody> entity) {
        String s = chapterLink.replaceAll("/", "%2F");
        String s1 = s.replaceAll("\\?", "%3F");
        RetrofitHelper.getService().getApi().getChapterBody(Constants.IMG_BASE_CHAPTER_URL + s1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ChapterBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ChapterBody chapterBody) {
                entity.onSuccess(chapterBody);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
