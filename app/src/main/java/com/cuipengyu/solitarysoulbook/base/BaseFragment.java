package com.cuipengyu.solitarysoulbook.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/8
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：fragment基类
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewLayoutId(), container, false);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ininData();
    }

    protected abstract void ininData();

    protected abstract int getContentViewLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);

    //fragment栈回退
    public void onBack() {
        getFragmentManager().popBackStack();
    }
}
