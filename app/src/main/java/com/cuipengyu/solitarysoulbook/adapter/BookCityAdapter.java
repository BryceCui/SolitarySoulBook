package com.cuipengyu.solitarysoulbook.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityAdapter extends BaseRvAdapter<BookCityBean> {
    public BookCityAdapter(BookCityBean data, @Nullable AdapterDelegateManager<BookCityBean> manager) {
        super(data, manager);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    /**
                     * 对类型进行判断
                     *  总共分成6份进行垂直排列
                     * new GridLayoutManager(this,6,GridLayoutManager.VERTICAL,false)
                     *  return 3 就是6/3=2 把itme分成2列
                     *  return 1 就是6/1=6 把item分成6列
                     *  gridManager.getSpanCount()就是获取设置的SpanCount也就是6 所以说就是铺满
                     */
                    switch (type) {
                        case 1:
                            return 2;
                        default:
                            return gridManager.getSpanCount();
                    }
                }
            });
        }
        super.onAttachedToRecyclerView(recyclerView);
    }
}
