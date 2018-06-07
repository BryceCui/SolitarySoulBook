package com.cuipengyu.solitarysoulbook.mvp.presenter;

import com.cuipengyu.solitarysoulbook.base.BaseHttpEntity;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingBookBean;
import com.cuipengyu.solitarysoulbook.mvp.controller.RankingBookController;
import com.cuipengyu.solitarysoulbook.mvp.model.RankingBookModel;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class RankingBookPresenter implements RankingBookController.RankingBookPresenter {
    private RankingBookController.RankingBookView bookView;
    private RankingBookController.RankingBookModel bookModel;

    public RankingBookPresenter(RankingBookController.RankingBookView View) {
        this.bookView = View;
        bookModel = new RankingBookModel();
        bookView.setPresenter(this);
    }

    @Override
    public void getData(String id) {
        bookView.LoadingShow();
        bookModel.getData(id, new BaseHttpEntity<RankingBookBean>() {
            @Override
            public void onSuccess(RankingBookBean data) {
                bookView.setData(data);
                bookView.LoadingDismiss();
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
