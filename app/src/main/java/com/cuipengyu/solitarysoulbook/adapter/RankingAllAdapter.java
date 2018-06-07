package com.cuipengyu.solitarysoulbook.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingAllBean;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class RankingAllAdapter extends AdapterDelegate<RankingAllBean> {
    @Override
    protected boolean isForViewType(RankingAllBean itmes, int position) {
        return true;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_ranking_string_layout, parent, false));

    }

    @Override
    protected void onBindViewHolder(final RankingAllBean itmes, final int position, BaseViewHolder holder) {
        if (position < itmes.getMale().size()) {
            holder.setText(R.id.book_ranking_string_tv, itmes.getMale().get(position).getTitle());
            holder.setOnClickListener(R.id.book_ranking_string_tv, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new EvenBusEntityBook(itmes.getMale().get(position).get_id(), Constants.INTENT_BOOKRANKING));
//                    Log.e("View1", position + "");
                }
            });
        }
//        {
//            final int sition = position - itmes.getMale().size() + 1;
//            holder.setText(R.id.book_ranking_string_tv, itmes.getFemale().get(position).getTitle());
//            holder.setOnClickListener(R.id.book_ranking_string_tv, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("View1", position + "");
//                }
//            });
//        }
    }

    @Override
    protected int ItemCount(RankingAllBean items) {
        return items.getMale().size();
    }
}
