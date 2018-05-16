package com.cuipengyu.solitarysoulbook.mvp.controller;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.base.BasePresenter;
import com.cuipengyu.solitarysoulbook.base.BaseView;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/24
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class ReadActivityController {
    public interface ReadPresenter extends BasePresenter {
        void getChapterListData(String booid);
    }

    public interface ReadModel {
        void getChapterListData(String bookid, BaseHttpEntity<ChapterLink> entity);
        void getChapterBody(String chapterLink, BaseHttpEntity entity);
    }

    public interface ReadView extends BaseView<ReadPresenter> {
        void setChapterListData(ChapterLink ChapterLink);
    }
}
