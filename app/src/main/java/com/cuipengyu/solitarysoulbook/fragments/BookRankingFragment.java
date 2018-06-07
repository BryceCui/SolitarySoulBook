package com.cuipengyu.solitarysoulbook.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.RankingBookAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseFragment;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingBookBean;
import com.cuipengyu.solitarysoulbook.mvp.controller.RankingBookController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.RankingBookPresenter;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookRankingFragment extends BaseFragment implements RankingBookController.RankingBookView {
    private String bookId;
    private RecyclerView mRecyclerView;
    private RankingBookController.RankingBookPresenter bookPresenter;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_ranking_tab_bookcity;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void initData() {
        bookId = getArguments().getString("bookid");
        new RankingBookPresenter(this);
        bookPresenter.getData(bookId);
        AdapterDelegateManager manager = new AdapterDelegateManager();
        manager.addDelegate(new RankingBookAdapter(getActivity()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    protected void initView() {
        mRecyclerView = findView(R.id.book_ranking_rv);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setData(RankingBookBean data) {
        AdapterDelegateManager manager = new AdapterDelegateManager();
        manager.addDelegate(new RankingBookAdapter(getActivity()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new BaseRvAdapter(data, manager));
    }

    @Override
    public void LoadingShow() {

    }

    @Override
    public void LoadingDismiss() {

    }

    @Override
    public void setPresenter(RankingBookController.RankingBookPresenter presenter) {
        this.bookPresenter = presenter;
    }
}
