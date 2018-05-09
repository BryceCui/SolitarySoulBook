package com.cuipengyu.solitarysoulbook.widget;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/9
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BindViewHolder  {
    private View bindView;
    private SparseArray<View> views;
    private CustomDialog dialog;
    public BindViewHolder(final View view) {
        this.bindView = view;
        this.views = new SparseArray<>();
    }

    BindViewHolder(View view, CustomDialog dialog) {
        this.bindView = view;
        this.dialog = dialog;
        views = new SparseArray<>();
    }
    private <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = bindView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
    public void addOnClickListener(@IdRes final int viewId) {
        final View view = getView(viewId);
        if (view != null) {
            if (!view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog.getOnViewClickListener() != null) {
                        dialog.getOnViewClickListener().onViewClick(BindViewHolder.this,view, dialog);
                    }
                }
            });
        }
    }
    @Deprecated
    public BindViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
