package com.cuipengyu.solitarysoulbook.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/8
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：fragment基类
 */
public abstract class BaseFragment extends Fragment {
    private View view = null;
    private Activity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getContentViewLayoutId(), container, false);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    protected abstract int getContentViewLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected <T extends View> T findView(int resId) {
        return (T) view.findViewById(resId);
    }

    public void replaceFragment(@IdRes int containerViewId, Fragment fragment) {
        replaceFragment(containerViewId, fragment, false);
    }

    public void replaceFragment(@IdRes int containerViewId, Fragment fragment, boolean isBackStack) {
        replaceFragment(getFragmentManager(), containerViewId, fragment, isBackStack);
    }

    private void replaceFragment(FragmentManager mManager, int containerViewId, Fragment fragment, boolean isBackStack) {
        FragmentTransaction mTransaction = mManager.beginTransaction();
        mTransaction.replace(containerViewId, fragment);
        if (isBackStack) mTransaction.addToBackStack(null);
        mTransaction.commit();
    }



    //fragment栈回退
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    public void finish() {
        mContext.finish();
    }

    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }
}
