package com.cuipengyu.solitarysoulbook.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.squareup.leakcanary.RefWatcher;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/9
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： dialogfragment 基类
 */
public abstract class BaseCustomDialog extends DialogFragment {
    public static final String TAG = "BaseCustomDialog";
    private View view;
    private static final float DEFAULT_DIMAMOUNT = 0.2F;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutRes() > 0) {
            view = inflater.inflate(getLayoutRes(), container, false);
        }
        if (getDialogView() != null) {
            view = getDialogView();
        }
        bindView(view);
        return view;
    }

    protected abstract int getLayoutRes();

    protected abstract View getDialogView();

    protected abstract void bindView(View view);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //去除Dialog默认头部
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        /**
         * setCanceledOnTouchOutside(false);调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
         * setCancelable(false);调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
         */
        setCancelable(isCancelable());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ApplicationContextUtil.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置窗体背景色透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置宽高
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //位置
            layoutParams.dimAmount=getDimAmount();
            layoutParams.gravity = getGravity();
            window.setAttributes(layoutParams);
        }
    }
    public float getDimAmount() {
        return DEFAULT_DIMAMOUNT;
    }
    //默认弹窗位置为中心
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
        LogUtils.e("清除view");
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }

    public String getFragmentTag() {
        return TAG;
    }

}
