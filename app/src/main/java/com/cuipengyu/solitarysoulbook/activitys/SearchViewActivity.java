package com.cuipengyu.solitarysoulbook.activitys;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.SearchHistoryAdapter;
import com.cuipengyu.solitarysoulbook.adapter.SearchHotWordAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchHisitoryBean;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;
import com.cuipengyu.solitarysoulbook.mvp.controller.SearchActivityController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.SearchActivityPresenter;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.cuipengyu.solitarysoulbook.widget.RvTitleItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

public class SearchViewActivity extends BaseActivity implements SearchView.OnCloseListener, SearchView.OnQueryTextListener, SearchActivityController.searchView {
    private RecyclerView mRecyclerView;
    private HotWord mHotWord;
    private SearchViewBean mSearchViewBean = null;
    private SearchActivityController.searchPresenter mSearchPresenter = null;
    private List<String> mTitleItems;

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
        mTitleItems = new ArrayList<>();
        mTitleItems.add("历史搜索");
        mSearchViewBean = new SearchViewBean();
        SearchHisitoryBean hisitoryBean = new SearchHisitoryBean();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "这是" + i + "条历史记录";
            strings.add(name);
        }
        hisitoryBean.setSearchName(strings);
        mSearchViewBean.setHisitoryBean(hisitoryBean);
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
        mSearchViewBean.setHotWord(hotWordData);
        AdapterDelegateManager<SearchViewBean> manager = new AdapterDelegateManager<SearchViewBean>();
        manager.addDelegate(new SearchHotWordAdapter());
        manager.addDelegate(new SearchHistoryAdapter());
        FlexboxLayoutManager flexboxLayoutManager=new FlexboxLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RvTitleItemDecoration(mTitleItems,80));
        mRecyclerView.setAdapter(new BaseRvAdapter<SearchViewBean>(mSearchViewBean, manager));
    }

    @Override
    public void setPresenter(SearchActivityController.searchPresenter presenter) {
        this.mSearchPresenter = presenter;
    }
}
