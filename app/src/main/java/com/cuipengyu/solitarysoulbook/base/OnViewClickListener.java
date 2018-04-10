package com.cuipengyu.solitarysoulbook.base;

import android.view.View;

import com.cuipengyu.solitarysoulbook.widget.BindViewHolder;
import com.cuipengyu.solitarysoulbook.widget.CustomDialog;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/9
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public interface OnViewClickListener {
    void onViewClick(BindViewHolder viewHolder, View view, CustomDialog tDialog);
}
