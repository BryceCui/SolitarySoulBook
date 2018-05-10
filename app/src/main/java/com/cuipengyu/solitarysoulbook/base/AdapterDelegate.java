package com.cuipengyu.solitarysoulbook.base;

import android.view.ViewGroup;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/10
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public abstract class AdapterDelegate<T> {
    /**
     * 对类型进行判断
     * judgment type
     *
     * @param itmes
     * @param position
     * @return
     */
    protected abstract boolean isForViewType(List<T> itmes, int position);

    protected abstract BaseViewHolder onCreateViewHolder(ViewGroup parent);

    protected abstract void onBindViewHolder(List<T> itmes, int position, BaseViewHolder holder);
}
