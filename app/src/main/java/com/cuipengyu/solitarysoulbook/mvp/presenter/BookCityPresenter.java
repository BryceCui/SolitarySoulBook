package com.cuipengyu.solitarysoulbook.mvp.presenter;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityRecommendBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCitySpread;
import com.cuipengyu.solitarysoulbook.mvp.controller.BookCityController;
import com.cuipengyu.solitarysoulbook.mvp.model.BookCityModel;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityPresenter implements BookCityController.BookCityPresenter {
    private BookCityController.BookCityModel model;
    private BookCityController.BookCityView view;

    public BookCityPresenter(BookCityController.BookCityView view) {
        this.view = view;
        model = new BookCityModel();
        view.setPresenter(this);
    }

    @Override
    public void getBookListSpread() {
        model.getBookListSpread(new BaseHttpEntity<BookCitySpread>() {
            @Override
            public void onSuccess(BookCitySpread data) {
                view.setBookListSpread(data);
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
    public void getBooKListRecommend() {
        model.getBookListRecommend(new BaseHttpEntity<BookCityRecommendBean>() {
            @Override
            public void onSuccess(BookCityRecommendBean data) {
                view.setBookListRecommend(data);
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
        getBookListSpread();
    }
}
