package com.cuipengyu.solitarysoulbook.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/9
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BaseRvAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {
    private static final int TYPE_TITLE = 0x0001;
    private static final int TYPE_EMPTY = 0x0002;
    private static final int TYPE_FOOTER = 0x0003;
    private static final int TYPE_HEADER = 0x0004;
    private static final int TYPE_NORMAL = 0x0005;
    private LinearLayout mHeaderLayout;
    private LinearLayout mFooterLayout;
    private LinearLayout mEmptyLayout;
    private LinearLayout mTitleLayout;
    private LayoutInflater mInflater;
    private List<T> mData;
    private int mLayoutResId;

    public BaseRvAdapter(@Nullable List<T> data) {
        this(0, data);
    }

    public BaseRvAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }
//
//    public BaseRvAdapter(@LayoutRes int layoutResId) {
//        this(layoutResId, null);
//    }

    @NonNull
    @Override
    public K onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        K baseViewHolder = null;
        this.mInflater=LayoutInflater.from(parent.getContext());
        switch (viewType){
            case TYPE_TITLE:


        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull K holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public int getFooterLayoutCount() {
        if (mFooterLayout == null || mFooterLayout.getChildCount() == 0) return 0;
        return 1;
    }

    public int getHeaderLayoutCount() {
        if (mHeaderLayout == null || mHeaderLayout.getChildCount() == 0) return 0;
        return 1;
    }

    public int getEmptyLayoutCount() {
        if (mEmptyLayout == null || mEmptyLayout.getChildCount() == 0) return 0;
        if (mData.size() != 0) return 0;
        return 1;
    }
}
