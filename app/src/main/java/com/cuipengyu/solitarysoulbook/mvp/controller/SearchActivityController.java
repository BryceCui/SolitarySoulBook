package com.cuipengyu.solitarysoulbook.mvp.controller;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.base.BasePresenter;
import com.cuipengyu.solitarysoulbook.base.BaseView;
import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchActivityController {
    public interface searchPresenter extends BasePresenter {
        void getHotWordData();

        void getAutoMatic(String query);
    }

    public interface searchView extends BaseView<searchPresenter> {
        void setHotWordData(HotWord hotWordData);

        void setAutoMaticData(AutomaticBean maticData);
    }

    public interface searchModel {
        void getHotWord(BaseHttpEntity<HotWord> httpEntity);

        void getAutoMaticSearch(String query, BaseHttpEntity<AutomaticBean> httpEntity);
    }
}
