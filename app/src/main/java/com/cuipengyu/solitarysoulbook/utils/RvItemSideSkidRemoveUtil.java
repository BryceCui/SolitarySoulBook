package com.cuipengyu.solitarysoulbook.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/13
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： RecycleView item 侧滑删除工具类
 */
public class RvItemSideSkidRemoveUtil extends HorizontalScrollView {
    /**
     * scale ：菜单占item的宽高比
     * mScreenWidth   ：屏幕宽
     * mMenuWidth  ：菜单宽
     */
    private static final float scale = 0.3f;
    private int mScreenWidth;
    private int mMenuWidth;
    private boolean once = true;

    public RvItemSideSkidRemoveUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = ScreenUtil.getAppWidth();
        mMenuWidth = (int) (mScreenWidth * scale);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (once) {
            LinearLayout layout = (LinearLayout) getChildAt(0);
            layout.getChildAt(0).getLayoutParams().width = mScreenWidth;
            layout.getChildAt(1).getLayoutParams().width = mMenuWidth;
            once = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (Math.abs(scrollX) > mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
