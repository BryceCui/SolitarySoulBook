package com.cuipengyu.solitarysoulbook.base;

import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/10
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 委托代理管理器 添加
 */
public abstract class AdapterDelegateManager<T> {
    protected SparseArray<AdapterDelegate<T>> mDelegates = new SparseArray<>();
    protected AdapterDelegate<T> mTAdapterDelegate;

    public AdapterDelegateManager<T> addDelegate(AdapterDelegate<T> data) {
        if (data == null) {
            throw new NullPointerException("AdapterDelegateManager to addDelegate is null");
        } else {
            int newItemType = mDelegates.size();
            mDelegates.put(newItemType, data);
        }
        return this;
    }

    public int getItemViewType(List<T> items, int position) {
        if (items == null) {
            throw new NullPointerException("Items is null on AdapterDelegateManager getItemViewType");
        }
        int delegateCount = mDelegates.size();
        for (int i = 0; i < delegateCount; i++) {
            //查看第几个位置的值：
            AdapterDelegate<T> delegate = mDelegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                //查看第几个位置的键：
                return mDelegates.keyAt(i);
            }
        }
        throw new NullPointerException("No AdapterDelegate added that matches position=" + position + " in data source");
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = mDelegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("on onCreateViewHolder delegate is null ");
        } else {
            return delegate.onCreateViewHolder(parent);
        }
    }

    public void onBindViewHolder(List<T> items, int position, BaseViewHolder viewHolder) {
        AdapterDelegate<T> delegate = mDelegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewHolder.getItemViewType());
        } else {
            delegate.onBindViewHolder(items, position, viewHolder);
        }
    }

}
