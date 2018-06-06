package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/24
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchTitleAdapter extends AdapterDelegate<SearchViewBean> {
    private int postion = 0;

    @Override
    protected boolean isForViewType(SearchViewBean itmes, int position) {
        return (position == 0) || position == postion + 1;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_titel_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchViewBean itmes, int position, BaseViewHolder holder) {
        if (position == 0 && itmes.getHotWord() != null) {
            holder.setText(R.id.searchview_text_item, "热门搜索");
        }
        if (itmes.getHotWord() != null) {
            postion = itmes.getHotWord().getHotWords().size();
        }
        if (position == postion + 1 && itmes.getmHisitoryBean().size() >0) {
            holder.setText(R.id.searchview_text_item, "历史记录");
        }
    }

    @Override
    protected int ItemCount(SearchViewBean items) {

        return (items.getHotWord() == null ? 0 : 1) + (items.getmHisitoryBean() == null ? 0 : 1);
    }
}
