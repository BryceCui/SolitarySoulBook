package com.cuipengyu.solitarysoulbook.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.cuipengyu.solitarysoulbook.utils.ScreenUtil;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： Recyclerview 自定义带Title的分割线类
 */
public class RvTitleItemDecoration extends RecyclerView.ItemDecoration {
    private List<String> mData;
    private int mTitleHeight = 0;
    private Paint mPaint;
    private Rect mRect;


    public RvTitleItemDecoration(List<String> data) {
        this(data, 40);
    }

    public RvTitleItemDecoration(List<String> data, int height) {
        mPaint = new Paint();
        mRect = new Rect();
        this.mData = data;
        this.mTitleHeight = height;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewAdapterPosition();
            int j = 0;
            if (isShow(position, parent, childCount)) {
                if (mData.size() > 1) {
                    ++j;
                    drawTitleArea(c, left, right, child, params, j);
                } else {
                    drawTitleArea(c, left, right, child, params, 0);
                }
            }
        }


    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int child = parent.getAdapter().getItemCount();
        if (isShow(position, parent, child)) {
            outRect.set(0, mTitleHeight, 0, 0);
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }

    //
    private boolean isShow(int position, RecyclerView parent, int c) {
//        if (position == 0) {
//            return true;
//        }
        int type = parent.getAdapter().getItemViewType(position);
        if (position > 0 && position < c) {
            int typeTwo = parent.getAdapter().getItemViewType(position - 1);
            if (type != typeTwo) {
                return true;
            }
        }
        return false;
    }

    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {//最先调用，绘制在最下层
        mPaint.setColor(Color.WHITE);
        c.drawRect(left, child.getTop() - params.topMargin - mTitleHeight, right, child.getTop() - params.topMargin, mPaint);
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(36);
        mPaint.getTextBounds(mData.get(position), 0, mData.get(position).length(), mRect);
        c.drawText(mData.get(position), child.getPaddingLeft(), child.getTop() - params.topMargin - (mTitleHeight / 2 - mRect.height() / 2), mPaint);
    }
}
