package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchHistoryAdapter extends AdapterDelegate<SearchViewBean> {
    @Override
    protected boolean isForViewType(SearchViewBean itmes, int position) {
//        return position > 19;
        return true;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_hisitory_rv_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchViewBean itmes, int position, BaseViewHolder holder) {
        int p = position - itmes.getHotWord().getHotWords().size();
        holder.setText(R.id.search_hisitory_name, itmes.getHisitoryBean().getSearchName().get(p) + "");
    }

    @Override
    protected int ItemCount(SearchViewBean items) {
        return items.getHisitoryBean().getSearchName().size();
    }
}
