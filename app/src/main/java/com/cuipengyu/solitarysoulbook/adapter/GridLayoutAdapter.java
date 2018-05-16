package com.cuipengyu.solitarysoulbook.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class GridLayoutAdapter extends BaseRvAdapter {
    public GridLayoutAdapter(List data, @Nullable AdapterDelegateManager manager) {
        super(data, manager);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Log.e("getChildCount",recyclerView.getChildCount()+"");
    }
}
