package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchHotWordAdapter extends AdapterDelegate<SearchViewBean> {
    View view;

    @Override
    protected boolean isForViewType(SearchViewBean itmes, int position) {
        if (itmes.getHotWord() != null) {
            return position < itmes.getHotWord().getHotSize() + 1;
        }
        return false;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_hotwoed_rv_item, parent, false);
        return new HotWordHolder(view);
    }

    @Override
    protected void onBindViewHolder(SearchViewBean itmes, int position, BaseViewHolder holder) {
        holder.setText(R.id.search_tv, itmes.getHotWord().getHotWords().get(position - 1));
    }

    @Override
    protected int ItemCount(SearchViewBean item) {
        return item.getHotWord() == null ? 0 : item.getHotWord().getHotSize();
    }

    class HotWordHolder extends BaseViewHolder {

        public HotWordHolder(View itemView) {
            super(itemView);
        }
    }
}
