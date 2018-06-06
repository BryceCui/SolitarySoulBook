package com.cuipengyu.solitarysoulbook.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.BookCityAdapter;
import com.cuipengyu.solitarysoulbook.adapter.BookCityRecomAdapter;
import com.cuipengyu.solitarysoulbook.adapter.BookCitySpreadAdapter;
import com.cuipengyu.solitarysoulbook.adapter.BookCityStringAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseFragment;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityRecommendBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCitySpread;
import com.cuipengyu.solitarysoulbook.mvp.controller.BookCityController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.BookCityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityFragment extends BaseFragment implements BookCityController.BookCityView {
    private RecyclerView book_city_rv;
    private List<String> strings = null;
    private BookCityController.BookCityPresenter presenter;
    private BookCityBean cityBean;
    private BookCityAdapter adapter;
    private AdapterDelegateManager manager=null;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_mian_tab_bookcity;
    }

    @Override
    protected void initData() {
        strings = new ArrayList<>();
        cityBean = new BookCityBean();
        strings.add("分类");
        strings.add("排行");
        strings.add("精选");
        cityBean.setStrings(strings);
        new BookCityPresenter(this);
        presenter.onStar();
        presenter.getBooKListRecommend();
        manager = new AdapterDelegateManager();
        manager.addDelegate(0, new BookCitySpreadAdapter());
        manager.addDelegate(1, new BookCityStringAdapter());
        manager.addDelegate(2, new BookCityRecomAdapter(getActivity()));
        book_city_rv.setLayoutManager(new GridLayoutManager(getActivity(), 6, GridLayoutManager.VERTICAL, false));
        book_city_rv.setAdapter(new BookCityAdapter(cityBean, manager));
    }

    @Override
    protected void initView() {
        book_city_rv = findView(R.id.book_city_rv);
    }

    @Override
    public void setBookListSpread(BookCitySpread spread) {
        cityBean.setCitySpread(spread);
    }

    @Override
    public void setBookListRecommend(BookCityRecommendBean recommendBean) {
        cityBean.setRecommendBean(recommendBean);

    }

    @Override
    public void LoadingShow() {

    }

    @Override
    public void LoadingDismiss() {

    }

    @Override
    public void setPresenter(BookCityController.BookCityPresenter presenter) {
        this.presenter = presenter;
    }
}
