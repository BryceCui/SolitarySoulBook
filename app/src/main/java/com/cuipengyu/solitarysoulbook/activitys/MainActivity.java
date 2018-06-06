package com.cuipengyu.solitarysoulbook.activitys;


import android.app.FragmentManager;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.fragments.BookCityFragment;
import com.cuipengyu.solitarysoulbook.fragments.BookShelfFragment;
import com.cuipengyu.solitarysoulbook.fragments.SettingFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private BookShelfFragment mShelfFragment;
    private BookCityFragment mCityFragment;
    private SettingFragment mSettingFragment;
    private FragmentManager mManager;
    private TextView my_setting_tv, book_city_tv, bookshelf_tv;
    private ImageView bookshelf_iv, book_city_iv, my_setting_iv;
    private TextView base_toolbar_menu;

    @Override
    public int bindViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        my_setting_tv = findViewById(R.id.my_setting_tv);
        book_city_tv = findViewById(R.id.book_city_tv);
        bookshelf_tv = findViewById(R.id.bookshelf_tv);
        bookshelf_iv = findViewById(R.id.bookshelf_iv);
        book_city_iv = findViewById(R.id.book_city_iv);
        my_setting_iv = findViewById(R.id.my_setting_iv);
        base_toolbar_menu = findViewById(R.id.base_toolbar_menu);
    }

    @Override
    public void initData() {

        initFragments();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * initialization  fragment bottom tab
     * one FragmentTransaction only  add one fragment
     */
    private void initFragments() {
        base_toolbar_menu.setOnClickListener(this);
        mShelfFragment = new BookShelfFragment();
        mCityFragment = new BookCityFragment();
        mSettingFragment = new SettingFragment();
        mManager = getFragmentManager();
        mManager.beginTransaction().add(R.id.main_fl, mShelfFragment, "mShelfFragment").commit();
        mManager.beginTransaction().add(R.id.main_fl, mCityFragment, "mCityFragment").commit();
        mManager.beginTransaction().add(R.id.main_fl, mSettingFragment, "mSettingFragment").commit();
        setTabColor(0);
    }

    /**
     * setting  bottom tab status and color
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
                mManager.beginTransaction().show(mShelfFragment).hide(mSettingFragment).hide(mCityFragment).commit();
                setTitleBar("书架");
                setRightImage(R.drawable.searchview_bg);
                break;
            case 1:
                bookshelf_tv.setSelected(false);
                bookshelf_iv.setSelected(false);
                book_city_tv.setSelected(true);
                book_city_iv.setSelected(true);
                my_setting_tv.setSelected(false);
                my_setting_iv.setSelected(false);
                    mManager.beginTransaction().show(mCityFragment).hide(mSettingFragment).hide(mShelfFragment).commit();
                setTitleBar("书城");
                setRightImage(R.drawable.searchview_bg);
                break;
            case 2:
                bookshelf_tv.setSelected(false);
                bookshelf_iv.setSelected(false);
                book_city_tv.setSelected(false);
                book_city_iv.setSelected(false);
                my_setting_tv.setSelected(true);
                my_setting_iv.setSelected(true);
                    mManager.beginTransaction().show(mSettingFragment).hide(mCityFragment).hide(mShelfFragment).commit();

                setTitleBar("个人中心");
                setRightImage(0);
                break;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bookshelf_rl:
                setTabColor(0);
                break;
            case R.id.book_city_rl:
                setTabColor(1);
                break;
            case R.id.my_setting_rl:
                setTabColor(2);
                break;
            case R.id.base_toolbar_menu:
                //点击搜索跳转搜索页面操作
                Intent intent = new Intent(this, SearchViewActivity.class);
                startActivity(intent);
                break;
        }
    }

}
