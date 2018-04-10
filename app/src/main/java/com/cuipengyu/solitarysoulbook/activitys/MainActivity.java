package com.cuipengyu.solitarysoulbook.activitys;


import android.util.Log;
import android.view.View;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.model.bean.MixTocBeanCon;
import com.cuipengyu.solitarysoulbook.model.httphelper.HttpEngine;
import com.cuipengyu.solitarysoulbook.model.httphelper.RetrofitHelper;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends BaseActivity {
    @Override
    public int bindViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://www.zhuishushenqi.com/").get();
                    Elements elements = document.select("div.recommend").select("div.books-list");
                    Elements links = elements.select("a[href]");
                    LogUtils.e(links.select("a").attr("href")+"");
                    Log.e("links",links.get(0).select("a").attr("href")+"");
                    Log.e("links",links.get(1).select("a").attr("href")+"");
                    Log.e("links",links.get(2).select("a").attr("href")+"");
                    Log.e("links",links.get(3).select("a").attr("href")+"");
                    Log.e("links",links.size()+"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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
