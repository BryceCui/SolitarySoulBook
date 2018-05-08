package com.cuipengyu.solitarysoulbook.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.cuipengyu.solitarysoulbook.R;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/8
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class CommonToolbar extends Toolbar {
    private TextView base_toolbar_back, base_toolbar_title, base_toolbar_menu;

    public CommonToolbar(Context context) {
        super(context);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * called this method when loading xml finish
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        base_toolbar_back = findViewById(R.id.base_toolbar_back);
        base_toolbar_title = findViewById(R.id.base_toolbar_title);
        base_toolbar_menu = findViewById(R.id.base_toolbar_menu);
    }

    //设置左边图片
    public void setLeftIamge() {
        setLeftIamge(R.drawable.back);
    }

    public void setLeftIamge(@DrawableRes int idRes) {
        Drawable image = getResources().getDrawable(idRes, null);
        image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
        base_toolbar_back.setCompoundDrawables(image, null, null, null);
    }

    //设置左边返回字
    public void setLeftText() {
        setLeftText("返回");
    }

    public void setLeftText(String text) {
        base_toolbar_back.setText(text);
    }

    public void setTitle(String title) {
        base_toolbar_title.setGravity(Gravity.CENTER);
        base_toolbar_title.setText(title);
    }

    public void setRightText(String text) {
        base_toolbar_menu.setGravity(Gravity.RIGHT);
        base_toolbar_menu.setText(text);
    }

    public void setRightImage(int idRes) {
        Drawable image = getResources().getDrawable(idRes, null);
        image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
        base_toolbar_back.setCompoundDrawables(null, null, image, null);
    }

    public void setLeftListener(OnClickListener listener) {
        base_toolbar_back.setOnClickListener(listener);
    }

    public void setRightListener(OnClickListener listener) {
        base_toolbar_menu.setOnClickListener(listener);
    }

    public void setLeftVisibility(int visibility) {
        base_toolbar_back.setVisibility(visibility);
    }

    public void setTitleVisibility(int visibility) {
        base_toolbar_title.setVisibility(visibility);
    }

    public void setRightVisibility(int visibility) {
        base_toolbar_menu.setVisibility(visibility);
    }

    //设置toolbar状态栏颜色
    public void setToolbarBackground(int res) {
        this.setBackgroundResource(res);
    }
}
