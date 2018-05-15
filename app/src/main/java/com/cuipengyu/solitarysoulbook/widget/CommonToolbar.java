package com.cuipengyu.solitarysoulbook.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cuipengyu.solitarysoulbook.R;

import java.lang.reflect.Field;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/8
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class CommonToolbar extends Toolbar {
    private TextView base_toolbar_back, base_toolbar_title, base_toolbar_menu;
    private SearchView mSearchView;

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
    public void searchInit(){
        mSearchView = findViewById(R.id.base_toolbar_search);
        mSearchView.setQueryHint("请输入作者或者书名搜索");
        //设置搜索图标是否在框内
        mSearchView.setIconifiedByDefault(false);
        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(true);
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

    public void setLeftText(String text) {
        base_toolbar_back.setText(text);
    }

    public void setTitleBar(String title) {
        base_toolbar_title.setGravity(Gravity.CENTER);
        base_toolbar_title.setText(title);
    }

    public void setRightText(String text) {
        base_toolbar_menu.setGravity(Gravity.RIGHT);
        base_toolbar_menu.setText(text);
    }

    public void setRightImage(int idRes) {
        if (idRes != 0) {
            Drawable image = getResources().getDrawable(idRes, null);
            image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
            base_toolbar_menu.setCompoundDrawables(null, null, image, null);
        } else {
            base_toolbar_menu.setCompoundDrawables(null, null, null, null);
        }
    }

    public void setLeftListener(OnClickListener listener) {
        base_toolbar_back.setOnClickListener(listener);
    }

    public void setRightListener(OnClickListener listener) {
        base_toolbar_menu.setOnClickListener(listener);
    }

    public void setSearchVisibility(int visibility) {
        mSearchView.setVisibility(visibility);
        mSearchView.setVisibility(VISIBLE);
    }

    //设置点击搜索框右边叉叉的事件
    public void setSearchCloseListener(SearchView.OnCloseListener listener) {
        mSearchView.setOnCloseListener(listener);
    }
    //设置文字变化监听事件
    public void setQueryTextListener(SearchView.OnQueryTextListener listener){
        mSearchView.setOnQueryTextListener(listener);
    }
    public void setSearchUnderlineTransparency(){
        setUnderLinetransparent(mSearchView);
    }

    private void setUnderLinetransparent(SearchView searchView){
        try {
            Class<?> argClass = searchView.getClass();
            // mSearchPlate是SearchView父布局的名字
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            ownField.setAccessible(true);
            View mView = (View) ownField.get(searchView);
            mView.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
