package com.cuipengyu.solitarysoulbook.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class HotWordAdapter extends AdapterDelegate<HotWord> {

    @Override
    protected boolean isForViewType(HotWord itmes, int position) {
        return true;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_rv_hotwoed, parent, false));
    }

    @Override
    protected void onBindViewHolder(HotWord itmes, int position, BaseViewHolder holder) {
        Log.e("search_tv", itmes.getHotWords().get(position));
        holder.setText(R.id.search_tv, itmes.getHotWords().get(position));
    }

    @Override
    protected int ItemCount(HotWord item) {
        Log.e("ItemCount", item.getHotWords().size() + "");
        return item.getHotWords().size();
    }
}
