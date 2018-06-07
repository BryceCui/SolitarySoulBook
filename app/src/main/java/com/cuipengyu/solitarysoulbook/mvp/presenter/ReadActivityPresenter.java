package com.cuipengyu.solitarysoulbook.mvp.presenter;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterBody;
import com.cuipengyu.solitarysoulbook.mvp.controller.ReadActivityController;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.mvp.model.ReadActivityModel;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/24
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class ReadActivityPresenter implements ReadActivityController.ReadPresenter {
    private ReadActivityController.ReadView mReadView;
    private ReadActivityController.ReadModel mReadModel;

    public ReadActivityPresenter(ReadActivityController.ReadView mView) {
        this.mReadView = mView;
        mReadModel = new ReadActivityModel();
        mReadView.setPresenter(this);
    }

    @Override
    public void getChapterListData(String booid) {
        mReadView.LoadingShow();
        mReadModel.getChapterListData(booid, new BaseHttpEntity<ChapterLink>() {
            @Override
            public void onSuccess(ChapterLink data) {
                mReadView.setChapterListData(data);
                mReadView.LoadingDismiss();
            }

            @Override
            public void onError(String error) {
                mReadView.LoadingDismiss();
            }

            @Override
            public void onFinish() {
                mReadView.LoadingDismiss();
            }
        });

    }

    @Override
    public void getChapterBodyData(String data, final int Chapter) {
        mReadModel.getChapterBody(data, new BaseHttpEntity<ChapterBody>() {
            @Override
            public void onSuccess(ChapterBody data) {
                mReadView.setChapterBody(data, Chapter);
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void onStar() {

    }
}
