package com.cuipengyu.solitarysoulbook.mvp.controller;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.base.BasePresenter;
import com.cuipengyu.solitarysoulbook.base.BaseView;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingBookBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class RankingBookController {
    public interface RankingBookModel {
        void getData(String id, BaseHttpEntity<RankingBookBean> httpEntity);
    }

    public interface RankingBookView extends BaseView<RankingBookPresenter> {
        void setData(RankingBookBean data);
    }

    public interface RankingBookPresenter extends BasePresenter {
        void getData(String id);
    }
}
