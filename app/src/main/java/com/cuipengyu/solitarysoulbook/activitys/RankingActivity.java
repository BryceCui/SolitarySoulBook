package com.cuipengyu.solitarysoulbook.activitys;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.RankingAllAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingAllBean;
import com.cuipengyu.solitarysoulbook.fragments.BookRankingFragment;
import com.cuipengyu.solitarysoulbook.mvp.controller.RankingController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.RankingPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RankingActivity extends BaseActivity implements RankingController.RankingView {
    private RecyclerView mRecyclerView;
    private FrameLayout ranking_fragment;
    private RankingController.RankingPresenter presenter;
    private RankingAllBean allBean;
    private FragmentManager fragmentManager;
    private BookRankingFragment bookRankingFragment;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public int bindViewLayout() {
        return R.layout.activity_ranking;
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.ranking_rv);
        ranking_fragment = findViewById(R.id.ranking_fragment);
    }

    @Override
    public void initData() {
        allBean = new RankingAllBean();
        fragmentManager = getFragmentManager();
        new RankingPresenter(this);
        presenter.onStar();

    }

    @Override
    public void setAllRanking(RankingAllBean bean) {
        allBean = bean;
        AdapterDelegateManager manager = new AdapterDelegateManager();
        manager.addDelegate(new RankingAllAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new BaseRvAdapter<RankingAllBean>(bean, manager));
        Bundle bundle = new Bundle();
        String bookid = bean.getMale().get(0).get_id();
        bundle.putString("bookid", bookid);
        bookRankingFragment = new BookRankingFragment();
        bookRankingFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.ranking_fragment, bookRankingFragment).commit();
    }

    @Override
    public void setPresenter(RankingController.RankingPresenter presenter) {
        this.presenter = presenter;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostBook(EvenBusEntityBook entityBook) {
        if (entityBook.getType() == Constants.INTENT_BOOKRANKING) {
            bookRankingFragment = null;
            Bundle bundle = new Bundle();
            bundle.putString("bookid", entityBook.getBookName());
            bookRankingFragment = new BookRankingFragment();
            bookRankingFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.ranking_fragment, bookRankingFragment).commit();
        }
        if (entityBook.getType() == Constants.INTENT_BOOKRANKINGDETATILS) {
            Intent intent = new Intent(this, BookDetailsActivity.class);
            intent.putExtra("bookUrl", entityBook.getBookName());
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
