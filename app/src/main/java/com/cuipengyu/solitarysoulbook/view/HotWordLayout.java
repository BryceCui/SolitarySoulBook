package com.cuipengyu.solitarysoulbook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/17
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class HotWordLayout extends ViewGroup {

    public HotWordLayout(Context context) {
        this(context, null);
    }

    public HotWordLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HotWordLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //FlowLayout最终的宽度和高度值
        int resultWidth = 0;
        int resultHeight = 0;
        //测量时每一行的宽度
        int lineWidth = 0;
        //测量时每一行的高度，加起来就是FlowLayout的高度
        int lineHeight = 0;
        int childCount = getChildCount();
        //遍历每个子view
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量每一子view的宽高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int realChildWidth = childWidth + mlp.leftMargin + mlp.rightMargin;
            int realChildHeight = childHeight + mlp.topMargin + mlp.bottomMargin;
            //如果当前一行的宽度加上要加入的子view的宽度大于父容器给的宽度，就换行
            if ((lineWidth + realChildWidth) > sizeWidth) {
                resultWidth = Math.max(lineWidth, realChildWidth);
                resultHeight += realChildHeight;
                //换行了，lineWidth和lineHeight重新算
                lineWidth = realChildWidth;
                lineHeight = realChildHeight;
            } else {
                //不换行，直接相加
                lineWidth += realChildWidth;
                //每一行的高度取二者最大值
                lineHeight = Math.max(lineHeight, realChildHeight);
            }
            //遍历到最后一个的时候，肯定走的是不换行
            if (i == childCount - 1) {
                resultWidth = Math.max(lineWidth, resultWidth);
                resultHeight += lineHeight;
            }
            /**
             *  保存测量的宽高值
             *  Mode模式{
             1.   MeasureSpec.UNSPECIFIED 没有指定大小
             2.   MeasureSpec.EXACTLY   指 match_parent或者具体指定的大小
             3.   MeasureSpec.AT_MOST   指 wrap-content
             }
             */
            setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : resultWidth, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : resultHeight);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int flowWidth = getWidth();
        int childLeft = 0;
        int childTop = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            if (childLeft + mlp.leftMargin + childWidth + mlp.rightMargin > flowWidth) {
                //换行处理
                childTop += (mlp.topMargin + childHeight + mlp.bottomMargin);
                childLeft = 0;
            }
            //布局
            int left = childLeft + mlp.leftMargin;
            int top = childTop + mlp.topMargin;
            int right = childLeft + mlp.leftMargin + childWidth;
            int bottom = childTop + mlp.topMargin + childHeight;
            childView.layout(left, top, right, bottom);
            childLeft += (mlp.leftMargin + childWidth + mlp.rightMargin);
        }
    }
}
