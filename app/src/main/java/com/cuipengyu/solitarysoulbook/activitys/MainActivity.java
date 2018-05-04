package com.cuipengyu.solitarysoulbook.activitys;


import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends BaseActivity {
    FrameLayout main_fl;
    TextView my_setting_tv, book_city_tv, bookshelf_tv;
    ImageView bookshelf_iv, book_city_iv, my_setting_iv;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ApplicationContextUtil.getRefWatcher(this);
        refWatcher.watch(this);
    }

    @Override
    public int bindViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        main_fl = findViewById(R.id.main_fl);
        my_setting_tv = findViewById(R.id.my_setting_tv);
        book_city_tv = findViewById(R.id.book_city_tv);
        bookshelf_tv = findViewById(R.id.bookshelf_tv);
        bookshelf_iv = findViewById(R.id.bookshelf_iv);
        book_city_iv = findViewById(R.id.book_city_iv);
        my_setting_iv = findViewById(R.id.my_setting_iv);
    }

    @Override
    public void initData() {

    }

    public void main_onClick(View view) {
        switch (view.getId()) {
            case R.id.bookshelf_rv:
                setTabColor(0);
                break;
            case R.id.book_city_rv:
                setTabColor(1);
                break;
            case R.id.my_setting_rv:
                setTabColor(2);
                break;
        }
    }

    /**
     * 设置底部导航选中和未选中的状态
     *
     * @param index
     */
    public void setTabColor(int index) {
        switch (index) {
            case 0:
                bookshelf_tv.setSelected(true);
                bookshelf_iv.setSelected(true);
                book_city_tv.setSelected(false);
                book_city_iv.setSelected(false);
                my_setting_tv.setSelected(false);
                my_setting_iv.setSelected(false);
                break;
            case 1:
                bookshelf_tv.setSelected(false);
                bookshelf_iv.setSelected(false);
                book_city_tv.setSelected(true);
                book_city_iv.setSelected(true);
                my_setting_tv.setSelected(false);
                my_setting_iv.setSelected(false);
                break;
            case 2:
                bookshelf_tv.setSelected(false);
                bookshelf_iv.setSelected(false);
                book_city_tv.setSelected(false);
                book_city_iv.setSelected(false);
                my_setting_tv.setSelected(true);
                my_setting_iv.setSelected(true);
                break;

        }
    }
}
