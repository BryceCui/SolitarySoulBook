package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityBean;
import com.cuipengyu.solitarysoulbook.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCitySpreadAdapter extends AdapterDelegate<BookCityBean> {
    @Override
    protected boolean isForViewType(BookCityBean itmes, int position) {
        return position == 0;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_city_spread_layout, parent, false));

    }

    @Override
    protected void onBindViewHolder(BookCityBean itmes, int position, BaseViewHolder holder) {
        List<String> url = new ArrayList<>();
        int size = itmes.getCitySpread().getData().size();
        for (int i = 0; i < size; i++) {
            url.add(itmes.getCitySpread().getData().get(i).getImg());
        }
        holder.setImageBanner(url);
    }

    @Override
    protected int ItemCount(BookCityBean items) {
        return items.getCitySpread().getData()==null?0:1;
    }
}
