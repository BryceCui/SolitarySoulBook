package com.cuipengyu.solitarysoulbook.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityBean;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityRecomAdapter extends AdapterDelegate<BookCityBean> {
    private Context context;

    public BookCityRecomAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isForViewType(BookCityBean itmes, int position) {
        return position > itmes.getStrings().size();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_city_recom_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(final BookCityBean itmes, final int position, BaseViewHolder holder) {
        final int p = position - itmes.getStrings().size() - 1;
        Log.e("position-----", position + "");
        Log.e("p-----", p + "");
        holder.setText(R.id.bookrecom_bookName_tv, itmes.getRecommendBean().getBooks().get(p).getTitle());
        holder.setText(R.id.bookrecom_bookCat_tv, itmes.getRecommendBean().getBooks().get(p).getMajorCate());
        holder.setText(R.id.bookrecom_book_author_tv, itmes.getRecommendBean().getBooks().get(p).getAuthor());
        holder.setText(R.id.bookrecom_book_lastChapter_tv, itmes.getRecommendBean().getBooks().get(p).getLastChapter());
        holder.setText(R.id.bookrecom_book_wordCount_tv, itmes.getRecommendBean().getBooks().get(p).getChaptersCount() + "");
        holder.setImageGlide(R.id.book_city_recom_imag, Constants.IMG_BASE_URL + itmes.getRecommendBean().getBooks().get(p).getCover(), context);
        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EvenBusEntityBook(itmes.getRecommendBean().getBooks().get(p).getTitle()));
            }
        });
    }

    @Override
    protected int ItemCount(BookCityBean items) {
        return items.getRecommendBean().getBooks().size();
    }
}
