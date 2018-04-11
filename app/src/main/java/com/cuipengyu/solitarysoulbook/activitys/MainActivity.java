package com.cuipengyu.solitarysoulbook.activitys;


import android.view.View;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.SelectBean;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.model.httphelper.JsoupEngine;
import com.cuipengyu.solitarysoulbook.model.httphelper.JsoupHelper;
import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends BaseActivity {
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_watch:
                JsoupHelper.getHelper().getHtmlStringData("bzrt", 1, new JsoupEngine.CallBack<SelectBean>() {
                    @Override
                    public void onSucces(SelectBean baseBean) {

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

    }
}
