package com.cuipengyu.solitarysoulbook.activitys;

import android.support.v7.widget.SearchView;
import android.util.Log;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;

import static android.view.View.VISIBLE;

public class SearchViewActivity extends BaseActivity implements SearchView.OnCloseListener, SearchView.OnQueryTextListener {


    @Override
    public int bindViewLayout() {
        return R.layout.activity_search_view;
    }

    @Override
    public void initView() {
        initSearch();
    }

    @Override
    public void initData() {
        setLeftImage();
        setLeftBarBack();
        setSearchVisibility(VISIBLE);
        setSearchCloseListener(this);
        setQueryTextListener(this);

    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("onQueryTextSubmit", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("onQueryTextChange", newText);
        return false;
    }
}
