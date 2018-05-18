package com.cuipengyu.solitarysoulbook.activitys;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.HotWordAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.mvp.controller.SearchActivityController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.SearchActivityPresenter;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.google.android.flexbox.FlexboxLayoutManager;

import static android.view.View.VISIBLE;

public class SearchViewActivity extends BaseActivity implements SearchView.OnCloseListener, SearchView.OnQueryTextListener, SearchActivityController.searchView {

    private RecyclerView mRecyclerView;
    private HotWord mHotWord;
    private SearchActivityController.searchPresenter mSearchPresenter = null;

    @Override
    public int bindViewLayout() {
        return R.layout.activity_search_view;
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.search_rv);
    }

    @Override
    public void initData() {
        initSearch();
        setLeftImage();
        setLeftBarBack();
        setSearchVisibility(VISIBLE);
        setSearchCloseListener(this);
        setQueryTextListener(this);
        new SearchActivityPresenter(this);
        mSearchPresenter.onStar();
    }

    @Override
    public boolean onClose() {
        return false;
    }

    //键盘提交事件监听
    @Override
    public boolean onQueryTextSubmit(String query) {
        LogUtils.e(query);
        return false;
    }

    //edit 内容改变时的事件监听
    @Override
    public boolean onQueryTextChange(String newText) {
        LogUtils.e(newText);
        return false;
    }

    @Override
    public void setHotWordData(HotWord hotWordData) {
        this.mHotWord = hotWordData;
        AdapterDelegateManager<HotWord> manager = new AdapterDelegateManager<HotWord>();
        manager.addDelegate(new HotWordAdapter());
        mRecyclerView.setLayoutManager(new FlexboxLayoutManager(this));
        mRecyclerView.setAdapter(new BaseRvAdapter<HotWord>(hotWordData, manager));
    }

    @Override
    public void setPresenter(SearchActivityController.searchPresenter presenter) {
        this.mSearchPresenter = presenter;
    }
}
