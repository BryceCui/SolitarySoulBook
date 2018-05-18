package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class HotWordAdapter extends AdapterDelegate<HotWord> {
    View view;

    @Override
    protected boolean isForViewType(HotWord itmes, int position) {
        return true;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_hotwoed_rv_item, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(HotWord itmes, int position, BaseViewHolder holder) {
        holder.setText(R.id.search_tv, itmes.getHotWords().get(position));
    }

    @Override
    protected int ItemCount(HotWord item) {
        return item.getHotWords().size();
    }
}
