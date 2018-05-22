package com.cuipengyu.solitarysoulbook.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 通用recyclerview的分割线
 * Created by cuipengyu on 2018/3/27.
 */
public class RvItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public RvItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //如果是第一个Item加上top
        if (parent.getChildAdapterPosition(view) == 0) outRect.top = mSpace;
        //左下右的边距
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
    }
}
