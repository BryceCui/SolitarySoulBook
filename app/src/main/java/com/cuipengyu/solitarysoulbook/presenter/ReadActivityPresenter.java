package com.cuipengyu.solitarysoulbook.presenter;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.controller.ReadActivityController;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.model.ReadActivityModel;

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
    public void onStar() {
    }
}
