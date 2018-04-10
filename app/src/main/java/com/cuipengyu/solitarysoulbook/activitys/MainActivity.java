package com.cuipengyu.solitarysoulbook.activitys;


import android.util.Log;
import android.view.View;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.model.bean.MixTocBeanCon;
import com.cuipengyu.solitarysoulbook.model.httphelper.HttpEngine;
import com.cuipengyu.solitarysoulbook.model.httphelper.RetrofitHelper;

public class MainActivity extends BaseActivity {
    @Override
    public int bindViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_watch:
                LoadingShow();
                getdata();
                break;
            case R.id.btn_disss:
                LoadingDismiss();
                break;
        }
    }

    public void getdata() {
        RetrofitHelper.build().getServiceJson("mix-atoc/57206c3539a913ad65d35c7b", MixTocBeanCon.class, new HttpEngine.CallBack<MixTocBeanCon>() {
            @Override
            public void onSuccess(MixTocBeanCon mixTocBean) {
                Log.e("mix", mixTocBean.getMixToc().getChaptersUpdated() + "");
                LoadingDismiss();
            }

            @Override
            public void onError(String errMsg) {
                Log.e("errMsg", errMsg);

            }

            @Override
            public void onFailure(String result) {

            }
        });
    }
}
