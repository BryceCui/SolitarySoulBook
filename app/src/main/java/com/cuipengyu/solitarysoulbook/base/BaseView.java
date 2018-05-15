package com.cuipengyu.solitarysoulbook.base;


/**
 * mvp view接口所需继承的公共方法
 * Created by cuipengyu on 2018/3/10.
 */

public interface BaseView<T> {
    void LoadingShow();
    void LoadingDismiss();
    void setPresenter(T presenter);
}
