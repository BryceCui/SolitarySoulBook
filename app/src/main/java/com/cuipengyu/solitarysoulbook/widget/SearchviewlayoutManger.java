package com.cuipengyu.solitarysoulbook.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.cuipengyu.solitarysoulbook.utils.ScreenUtil;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/17
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchviewlayoutManger extends GridLayoutManager {

    public SearchviewlayoutManger(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SearchviewlayoutManger(Context context, int spanCount) {
        super(context, spanCount);
    }

    public SearchviewlayoutManger(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        int flowWidth = getWidth();
        int childLeft = 0;
        int childTop = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //获取到测量的宽和高
            final int childWidth = childView.getMeasuredWidth();
            Log.e("childWidth", childWidth + "");
            setSpanSizeLookup(new SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int a = ScreenUtil.getAppWidth() / childWidth;
                    LogUtils.e(a+"-----");
                    return a;
                }
            });
        }

    }
}
