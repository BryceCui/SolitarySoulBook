package com.cuipengyu.solitarysoulbook.mvp.presenter;

import android.util.Log;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.mvp.controller.SearchActivityController;
import com.cuipengyu.solitarysoulbook.mvp.model.SearchActivityModel;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchActivityPresenter implements SearchActivityController.searchPresenter {
    private SearchActivityController.searchView mSearchView;
    private SearchActivityController.searchModel mSearchModel;

    public SearchActivityPresenter(SearchActivityController.searchView mView) {
        this.mSearchView = mView;
        mSearchModel = new SearchActivityModel();
        mSearchView.setPresenter(this);
    }

    @Override
    public void onStar() {
        getHotWordData();
    }

    @Override
    public void getHotWordData() {
        mSearchModel.getHotWord(new BaseHttpEntity<HotWord>() {
            @Override
            public void onSuccess(HotWord data) {
                mSearchView.setHotWordData(data);
            }

            @Override
            public void onError(String error) {
                Log.e("SearchActivityPresenter", error);
            }

            @Override
            public void onFinish() {
                Log.e("SearchActivityPresenter", "onFinish");
            }
        });

    }

    @Override
    public void getAutoMatic(String query) {
        mSearchModel.getAutoMaticSearch(query, new BaseHttpEntity<AutomaticBean>() {
            @Override
            public void onSuccess(AutomaticBean data) {
                mSearchView.setAutoMaticData(data);
            }

            @Override
            public void onError(String error) {
                Log.e("SearchActivityPresenter", error);

            }

            @Override
            public void onFinish() {
                Log.e("SearchActivityPresenter", "onFinish");

            }
        });
    }
}
