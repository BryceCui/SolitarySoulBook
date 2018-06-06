package com.cuipengyu.solitarysoulbook.activitys;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.SearchAutomaticAdapter;
import com.cuipengyu.solitarysoulbook.adapter.SearchHistoryAdapter;
import com.cuipengyu.solitarysoulbook.adapter.SearchHotWordAdapter;
import com.cuipengyu.solitarysoulbook.adapter.SearchTitleAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;
import com.cuipengyu.solitarysoulbook.entity.bean.UserHisitoryBean;
import com.cuipengyu.solitarysoulbook.mvp.controller.SearchActivityController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.SearchActivityPresenter;
import com.cuipengyu.solitarysoulbook.utils.DbUtils;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.google.android.flexbox.FlexboxLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static android.view.View.VISIBLE;

public class SearchViewActivity extends BaseActivity implements SearchView.OnCloseListener, SearchView.OnQueryTextListener, SearchActivityController.searchView {
    private RecyclerView mRecyclerView;
    private SearchViewBean mSearchViewBean = null;
    private List<UserHisitoryBean> userHisitoryBean = null;
    private SearchActivityController.searchPresenter mSearchPresenter;
    private SearchView mSearchView;
    private BaseRvAdapter adapter = null;
    private AdapterDelegateManager<SearchViewBean> manager = null;
    private SearchTitleAdapter searchTitleAdapter = null;
    private SearchHotWordAdapter searchHotWordAdapter = null;
    private SearchHistoryAdapter searchHistoryAdapter = null;
    private SearchAutomaticAdapter searchAutomaticAdapter = null;
    private boolean isAutomatic = true;

    @Override
    public int bindViewLayout() {
        return R.layout.activity_search_view;
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.search_rv);
        mSearchView = findViewById(R.id.base_toolbar_search);
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        initSearch();
        setLeftImage();
        setLeftBarBack();
        setSearchVisibility(VISIBLE);
        setSearchCloseListener(this);
        setQueryTextListener(this);
        new SearchActivityPresenter(this);
        mSearchView.clearFocus();
        mSearchPresenter.onStar();
        mSearchViewBean = new SearchViewBean();
        userHisitoryBean = DbUtils.getSession().getUserHisitoryBeanDao().loadAll();
        manager = new AdapterDelegateManager<SearchViewBean>();
        searchTitleAdapter = new SearchTitleAdapter();
        searchHotWordAdapter = new SearchHotWordAdapter();
        searchHistoryAdapter = new SearchHistoryAdapter();
        searchAutomaticAdapter = new SearchAutomaticAdapter();
        manager.addDelegate(0, searchTitleAdapter);
        manager.addDelegate(1, searchHotWordAdapter);
        manager.addDelegate(2, searchHistoryAdapter);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        mRecyclerView.setLayoutManager(flexboxLayoutManager);
        adapter = new BaseRvAdapter(mSearchViewBean, manager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onClose() {
        return false;
    }

    //键盘提交事件监听
    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchPresenter.getAutoMatic(query);
        return false;
    }

    //edit 内容改变时的事件监听
    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.equals("")) {
            manager.addDelegate(0, searchTitleAdapter);
            manager.addDelegate(1, searchHotWordAdapter);
            manager.addDelegate(2, searchHistoryAdapter);
            manager.removeDelegate(3);
            mSearchViewBean.setAutomaticBean(null);
            adapter.notifyDataSetChanged();
        } else {
            isAutomatic = true;
            mSearchPresenter.getAutoMatic(newText);
        }
        return false;
    }

    @Override
    public void setHotWordData(HotWord hotWordData) {
        mSearchViewBean.setHotWord(hotWordData);
        mSearchViewBean.setmHisitoryBean(userHisitoryBean);
        adapter.notifyDataSetChanged();
    }

    //设置自动补全时的数据
    @Override
    public void setAutoMaticData(AutomaticBean maticData) {
        AutomaticBean mAutomaticBean = null;
        //是否已经添加和移除adapter
        if (isAutomatic) {
            manager.addDelegate(3, searchAutomaticAdapter);
            manager.removeDelegate(0);
            manager.removeDelegate(1);
            manager.removeDelegate(2);
            isAutomatic = false;
        }
        if (maticData != null) {
            mAutomaticBean = new AutomaticBean();
            mAutomaticBean = maticData;
            mSearchViewBean.setAutomaticBean(mAutomaticBean);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(SearchActivityController.searchPresenter presenter) {
        this.mSearchPresenter = presenter;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostBook(EvenBusEntityBook entityBook) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("bookName", entityBook.getBookName());
        if (entityBook.getBookUrl() != null) intent.putExtra("bookUrl", entityBook.getBookUrl());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
