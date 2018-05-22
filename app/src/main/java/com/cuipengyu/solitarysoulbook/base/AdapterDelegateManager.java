package com.cuipengyu.solitarysoulbook.base;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.utils.LogUtils;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/10
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 委托代理管理器 添加 ，对类型进行判断 创建布局和绑定数据
 */
public class AdapterDelegateManager<T> {
    private SparseArray<AdapterDelegate<T>> mDelegates = new SparseArray<>();
    public int getItemCount(T t) {
        int delegateCount = mDelegates.size();
        int count=0;
        for (int i = 0; i < delegateCount; i++) {
            //查看第几个位置的值：
            AdapterDelegate<T> delegate = mDelegates.valueAt(i);
            count+= delegate.ItemCount(t);
            Log.e("count",count+"");
        }
        return count;
    }

    public void addDelegate(AdapterDelegate<T> data) {
        int newItemType = mDelegates.size();
        addDelegate(newItemType, data);
    }

    public void addDelegate(int ViewType, AdapterDelegate<T> data) {
        if (data == null) {
            throw new NullPointerException("AdapterDelegateManager to addDelegate is null");
        }
        mDelegates.put(ViewType, data);
    }

    //没有指定类型时，直接添加根据添加顺序赋予类型
    public int getItemViewType(T items, int position) {
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

    //指定类型时调用获取类型的方法
    public int getViewType(@NonNull AdapterDelegate<T> data) {
        if (data == null) {
            throw new NullPointerException("getViewType is null on AdapterDelegateManager");
        }
        int index = mDelegates.indexOfValue(data);
        if (index == -1) {
            return -1;
        }
        return mDelegates.keyAt(index);
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = mDelegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("on onCreateViewHolder delegate is null ");
        } else {
            return delegate.onCreateViewHolder(parent);
        }
    }

    public void onBindViewHolder(T items, int position, BaseViewHolder viewHolder) {
        AdapterDelegate<T> delegate = mDelegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewHolder.getItemViewType());
        } else {
            delegate.onBindViewHolder(items, position, viewHolder);
        }
    }
}
