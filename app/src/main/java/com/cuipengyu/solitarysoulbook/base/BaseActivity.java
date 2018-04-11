package com.cuipengyu.solitarysoulbook.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.cuipengyu.solitarysoulbook.widget.CustomDialog;

import java.io.IOException;

public abstract class BaseActivity extends Activity {
    private final String TAG = getClass().getName();
    private CustomDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindViewLayout());
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog = null;
    }

    //绑定布局文件
    public abstract int bindViewLayout();

    //初始化控件
    public abstract void initView();

    //显示正在加载dialog
    public void LoadingShow() {
        dialog = new CustomDialog.Builder(getFragmentManager()).setLayoutRes(R.layout.dialog_baseloding_layout).setGravity(Gravity.CENTER).setCancelable(true).setDimount(0.1f).create().show();
    }

    //取消dialog
    public void LoadingDismiss() {
        if (dialog != null) dialog.dismiss();
        dialog = null;
    }
}
