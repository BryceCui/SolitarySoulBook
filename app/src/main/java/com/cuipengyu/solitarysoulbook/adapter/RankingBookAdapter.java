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
import com.cuipengyu.solitarysoulbook.entity.bean.RankingBookBean;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class RankingBookAdapter extends AdapterDelegate<RankingBookBean> {
    private Context context;
    public RankingBookAdapter(Context context) {
        this.context=context;
    }

    @Override
    protected boolean isForViewType(RankingBookBean itmes, int position) {
        return true;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_ranking_recom_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(final RankingBookBean itmes, final int position, BaseViewHolder holder) {
          holder.setImageGlide(R.id.book_ranking_recom_imag,itmes.getRanking().getBooks().get(position).getCover(),context);
          holder.setText(R.id.bookranking_bookName_tv,itmes.getRanking().getBooks().get(position).getTitle());
          holder.setText(R.id.bookranking_bookCat_tv,itmes.getRanking().getBooks().get(position).getMajorCate());
          holder.setText(R.id.bookranking_book_author_tv,itmes.getRanking().getBooks().get(position).getAuthor());
          holder.setOnClickListener(R.id.book_ranking_layout_recom, new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  EventBus.getDefault().post(new EvenBusEntityBook(itmes.getRanking().getBooks().get(position).get_id(), Constants.INTENT_BOOKRANKINGDETATILS));

              }
          });


    }

    @Override
    protected int ItemCount(RankingBookBean items) {
        return items.getRanking().getBooks().size();
    }
}
