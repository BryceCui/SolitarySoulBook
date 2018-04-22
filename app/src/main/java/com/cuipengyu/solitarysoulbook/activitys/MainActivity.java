package com.cuipengyu.solitarysoulbook.activitys;


import android.view.View;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.entity.bean.SelectBean;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.entity.httphelper.HttpEngine;
import com.cuipengyu.solitarysoulbook.entity.httphelper.JsoupEngine;
import com.cuipengyu.solitarysoulbook.entity.httphelper.JsoupHelper;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitBuilder;
import com.cuipengyu.solitarysoulbook.entity.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.squareup.leakcanary.RefWatcher;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_watch:
                JsoupHelper.getHelper().getHtmlStringData("bzrt", 1, new JsoupEngine.CallBack<SelectBean>() {
                    @Override
                    public void onSucces(SelectBean baseBean) {
                        LogUtils.e(baseBean.toString());
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
//                LoadingShow();
                break;
            case R.id.btn_disss:
                LoadingDismiss();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ApplicationContextUtil.getRefWatcher(this);
        refWatcher.watch(this);
    }

    @Override
    public int bindViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        RetrofitHelper.getService().getApi().getChapter("57206c3539a913ad65d35c7b").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ChapterLink>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChapterLink chapterLink) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
