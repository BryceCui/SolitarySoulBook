package com.cuipengyu.solitarysoulbook.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * App全局对象 ,manifest中声明name
 * Created by cuipengyu on 2018/3/14.
 */

public class ApplicationContextUtil extends Application {
    private static ApplicationContextUtil mApplicationContext;
    private RefWatcher refWatcher;

    //观察内存泄漏的获取方法
    public static RefWatcher getRefWatcher(Context context) {
        mApplicationContext = (ApplicationContextUtil) context.getApplicationContext();
        return mApplicationContext.refWatcher;
    }

    public static Context getAppConnect() {
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        Stetho.initializeWithDefaults(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            //            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
        // Normal app init code...
    }

}
