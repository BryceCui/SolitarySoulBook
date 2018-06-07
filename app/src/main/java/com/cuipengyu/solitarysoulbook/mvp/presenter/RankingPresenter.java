package com.cuipengyu.solitarysoulbook.mvp.presenter;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingAllBean;
import com.cuipengyu.solitarysoulbook.mvp.controller.RankingController;
import com.cuipengyu.solitarysoulbook.mvp.model.RankingModel;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class RankingPresenter implements RankingController.RankingPresenter {
    private RankingController.RankingModel rankingModel;
    private RankingController.RankingView mview;

    public RankingPresenter(RankingController.RankingView view) {
        mview = view;
        rankingModel = new RankingModel();
        mview.setPresenter(this);
    }

    @Override
    public void getAllRanking() {
        rankingModel.getAllBean(new BaseHttpEntity<RankingAllBean>() {
            @Override
            public void onSuccess(RankingAllBean data) {
                mview.setAllRanking(data);
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onFinish() {

            }
        });

    }

    @Override
    public void onStar() {
        getAllRanking();
    }
}
