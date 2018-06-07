package com.cuipengyu.solitarysoulbook.mvp.controller;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.base.BasePresenter;
import com.cuipengyu.solitarysoulbook.base.BaseView;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsId;

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

        void getidBook(String id, BaseHttpEntity<BookDetailsId> httpEntity);
    }

    public interface BookDeTailsPresenter extends BasePresenter {
        void setBookName(String bookName);

        void setidBook(String ID);
    }

    public interface BookDeTailsView extends BaseView<BookDeTailsPresenter> {
        void setBookData(BookDetailsBean data);

        void setidBook(BookDetailsId data);
    }
}
