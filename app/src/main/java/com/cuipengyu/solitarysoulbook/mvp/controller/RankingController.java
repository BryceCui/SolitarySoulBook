package com.cuipengyu.solitarysoulbook.mvp.controller;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.base.BasePresenter;
import com.cuipengyu.solitarysoulbook.base.BaseView;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingAllBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class RankingController {
    public interface RankingPresenter extends BasePresenter {
        void getAllRanking();
    }

    public interface RankingView extends BaseView<RankingPresenter> {
        void setAllRanking(RankingAllBean bean);
    }

    public interface RankingModel {
        void getAllBean(BaseHttpEntity<RankingAllBean> httpEntity);

    }
}
