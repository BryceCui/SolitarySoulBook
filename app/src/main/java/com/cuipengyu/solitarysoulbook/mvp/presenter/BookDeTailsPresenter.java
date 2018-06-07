package com.cuipengyu.solitarysoulbook.mvp.presenter;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsId;
import com.cuipengyu.solitarysoulbook.mvp.controller.BookDeTailsActivityController;
import com.cuipengyu.solitarysoulbook.mvp.model.BookDeTailsModel;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookDeTailsPresenter implements BookDeTailsActivityController.BookDeTailsPresenter {
    private BookDeTailsActivityController.BookDeTailsView mView;
    private BookDeTailsActivityController.BookDeTailsModel model;

    public BookDeTailsPresenter(BookDeTailsActivityController.BookDeTailsView mView) {
        this.mView = mView;
        model = new BookDeTailsModel();
        mView.setPresenter(this);
    }

    @Override
    public void setBookName(String bookName) {
        model.getBook(bookName, new BaseHttpEntity<BookDetailsBean>() {
            @Override
            public void onSuccess(BookDetailsBean data) {
                mView.setBookData(data);
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
    public void setidBook(String ID) {
        model.getidBook(ID, new BaseHttpEntity<BookDetailsId>() {
            @Override
            public void onSuccess(BookDetailsId data) {
                mView.setidBook(data);
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

    }
}
