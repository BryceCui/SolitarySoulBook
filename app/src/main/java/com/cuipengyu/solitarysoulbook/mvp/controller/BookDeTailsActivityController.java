package com.cuipengyu.solitarysoulbook.mvp.controller;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.base.BasePresenter;
import com.cuipengyu.solitarysoulbook.base.BaseView;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookDeTailsActivityController {
    public interface BookDeTailsModel {
        void getBook(String bookName, BaseHttpEntity<BookDetailsBean> httpEntity);
    }

    public interface BookDeTailsPresenter extends BasePresenter {
        void setBookName(String bookName);
    }

    public interface BookDeTailsView extends BaseView<BookDeTailsPresenter> {
        void setBookData(BookDetailsBean data);
    }
}
