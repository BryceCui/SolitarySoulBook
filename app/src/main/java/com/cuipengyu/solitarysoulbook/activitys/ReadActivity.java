package com.cuipengyu.solitarysoulbook.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.controller.ReadActivityController;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.presenter.ReadActivityPresenter;

public class ReadActivity extends BaseActivity implements ReadActivityController.ReadView {
    private ReadActivityController.ReadPresenter mReadActivityPresenter;

    @Override
    public int bindViewLayout() {
        return R.layout.activity_read;

    }

    @Override
    public void initData() {
        new ReadActivityPresenter(this);
        mReadActivityPresenter.getChapterListData("57206c3539a913ad65d35c7b");
    }

    @Override
    public void initView() {

    }

    @Override
    public void setChapterListData(ChapterLink ChapterLink) {

    }

    @Override
    public void lodingShow() {
        LoadingShow();
    }

    @Override
    public void lodingDissmis() {
        LoadingDismiss();
    }

    @Override
    public void setPresenter(ReadActivityController.ReadPresenter presenter) {
        this.mReadActivityPresenter = presenter;
    }
}
