package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityStringAdapter extends AdapterDelegate<BookCityBean> {
    @Override
    protected boolean isForViewType(BookCityBean itmes, int position) {
        return position > 0 && position <= itmes.getStrings().size();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_city_string_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(BookCityBean itmes, int position, BaseViewHolder holder) {
        holder.setText(R.id.book_city_string_tv, itmes.getStrings().get(position-1));
    }

    @Override
    protected int ItemCount(BookCityBean items) {
        return items.getStrings().size();
    }
}
