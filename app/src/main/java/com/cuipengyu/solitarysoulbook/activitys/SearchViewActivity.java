package com.cuipengyu.solitarysoulbook.activitys;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.SearchHistoryAdapter;
import com.cuipengyu.solitarysoulbook.adapter.SearchHotWordAdapter;
import com.cuipengyu.solitarysoulbook.adapter.SearchTitleAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchHisitoryBean;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;
import com.cuipengyu.solitarysoulbook.mvp.controller.SearchActivityController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.SearchActivityPresenter;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

public class SearchViewActivity extends BaseActivity implements SearchView.OnCloseListener, SearchView.OnQueryTextListener, SearchActivityController.searchView {
    private RecyclerView mRecyclerView;
    private HotWord mHotWord;
    private SearchViewBean mSearchViewBean = null;
    private SearchActivityController.searchPresenter mSearchPresenter;
    private List<String> mTitleItems;
    private SearchView mSearchView;
    private RecyclerView mSearch_auto_rv;
    private AutomaticBean mAutomaticBean;
    private BaseRvAdapter adapter = null;

    @Override
    public int bindViewLayout() {
        return R.layout.activity_search_view;
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.search_rv);
        mSearch_auto_rv = findViewById(R.id.search_auto_rv);
        mSearchView = findViewById(R.id.base_toolbar_search);
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
        mSearchView.clearFocus();

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
        mSearchPresenter.onStar();

        mAutomaticBean = new AutomaticBean();
//        mListPopupWindow.setDropDownGravity(Gravity.CENTER);

    }

    @Override
    public boolean onClose() {
        mSearchView.clearFocus();
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
//        if (newText.length() > 2) {
        mSearchPresenter.getAutoMatic(newText);

//        }

        return false;
    }

    @Override
    public void setHotWordData(HotWord hotWordData) {
        mSearchViewBean.setHotWord(hotWordData);
        AdapterDelegateManager<SearchViewBean> manager = new AdapterDelegateManager<SearchViewBean>();
        manager.addDelegate(new SearchTitleAdapter());
        manager.addDelegate(new SearchHotWordAdapter());
        manager.addDelegate(new SearchHistoryAdapter());
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        mRecyclerView.setLayoutManager(flexboxLayoutManager);
        adapter = new BaseRvAdapter(mSearchViewBean, manager);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void setAutoMaticData(AutomaticBean maticData) {
        this.mAutomaticBean = maticData;

    }

    @Override
    public void setPresenter(SearchActivityController.searchPresenter presenter) {
        this.mSearchPresenter = presenter;
    }
}
