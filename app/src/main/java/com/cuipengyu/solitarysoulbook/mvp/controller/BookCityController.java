package com.cuipengyu.solitarysoulbook.mvp.controller;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.base.BasePresenter;
import com.cuipengyu.solitarysoulbook.base.BaseView;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityRecommendBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCitySpread;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityController {
    public interface BookCityPresenter extends BasePresenter {
        void getBookListSpread();

        void getBooKListRecommend();
    }

    public interface BookCityView extends BaseView<BookCityPresenter> {
        void setBookListSpread(BookCitySpread spread);
        void setBookListRecommend(BookCityRecommendBean recommendBean);
    }

    public interface BookCityModel {
        void getBookListSpread(BaseHttpEntity<BookCitySpread> entity);
        void getBookListRecommend(BaseHttpEntity<BookCityRecommendBean> entity);
    }
}
