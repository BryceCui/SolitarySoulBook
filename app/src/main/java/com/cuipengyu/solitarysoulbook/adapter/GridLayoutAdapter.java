package com.cuipengyu.solitarysoulbook.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class GridLayoutAdapter extends BaseRvAdapter {
    private HotWord data;

    public GridLayoutAdapter(HotWord data, @Nullable AdapterDelegateManager manager) {
        super(data, manager);
        this.data = data;

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case 0:
                            Log.e("width", recyclerView.getChildCount()+ "");
                            int len = data.getHotWords().get(position).length();
                            if (len >= 6) {
                                return 2;
                            } else {
                                return 1;
                            }
                        default:
                            return gridLayoutManager.getSpanCount();
                    }
                }
            });
        }
    }
}
//                    recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                        @Override
//                        public boolean onPreDraw() {
//                            Log.e("onPreDraw", gridLayoutManager.getChildAt(0).getWidth() + "");
//                            return false;
//                        }
//                    });
//                    Log.e("width", getSpanSize(position) + "");
//                    gridLayoutManager.getChildAt(0).getWidth();