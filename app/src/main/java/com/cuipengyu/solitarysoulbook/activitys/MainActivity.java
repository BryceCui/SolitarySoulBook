package com.cuipengyu.solitarysoulbook.activitys;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.model.bean.MixTocBeanCon;
import com.cuipengyu.solitarysoulbook.model.httphelper.HttpEngine;
import com.cuipengyu.solitarysoulbook.model.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.squareup.leakcanary.RefWatcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_watch:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
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

    }
}
