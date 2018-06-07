package com.cuipengyu.solitarysoulbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.UserShelfBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class ShelfAdapter extends AdapterDelegate<List<UserShelfBean>> {
    private Context context;

    public ShelfAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isForViewType(List<UserShelfBean> itmes, int position) {
        return true;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_shelf_recom_layout, parent, false));

    }

    @Override
    protected void onBindViewHolder(final List<UserShelfBean> itmes, final int position, BaseViewHolder holder) {
        holder.setText(R.id.bookshelf_bookName_tv, itmes.get(position).getBookName());
        holder.setImageGlide(R.id.book_shelf_recom_imag, itmes.get(position).getBookImage(), context);
        holder.setOnClickListener(R.id.book_shelf_layout_recom, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EvenBusEntityBook(itmes.get(position).getBookUrl(), Constants.INTENT_BOOKRANKINGDETATILS));
            }
        });
    }

    @Override
    protected int ItemCount(List<UserShelfBean> items) {
        return items.size();
    }
}
