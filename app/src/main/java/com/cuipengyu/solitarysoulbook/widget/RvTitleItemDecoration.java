package com.cuipengyu.solitarysoulbook.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cuipengyu.solitarysoulbook.utils.LogUtils;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： Recyclerview 自定义带Title的分割线类
 */
public class RvTitleItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private Rect mRect;//用于存放测量文字
    private int mTitelHeight;
    private int mTitleFontSize;

    public RvTitleItemDecoration() {
        mPaint = new Paint();
        mRect = new Rect();
        mTitelHeight = 30;
        mTitleFontSize = 12;
    }

    public RvTitleItemDecoration(int height, int FonSize) {
        mPaint = new Paint();
        mRect = new Rect();
        mTitelHeight = height;
        mTitleFontSize = FonSize;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int type = parent.getAdapter().getItemViewType(position);
        LogUtils.e(type + "");

    }

    public boolean isForTitle(int position) {
        if (position == 0) {
            return true;
        } else return false;
    }
}
