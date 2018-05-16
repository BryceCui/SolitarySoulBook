package com.cuipengyu.solitarysoulbook.activitys;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.mvp.controller.ReadActivityController;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.mvp.presenter.ReadActivityPresenter;

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
    public void setPresenter(ReadActivityController.ReadPresenter presenter) {
        this.mReadActivityPresenter = presenter;
    }
}
