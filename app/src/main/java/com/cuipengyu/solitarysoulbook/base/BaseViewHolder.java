package com.cuipengyu.solitarysoulbook.base;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/9
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViewArray;
    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mViewArray=new SparseArray<>();
    }
    //绑定控件
    public <T extends View> T getView(@IdRes int Ids){
        View view=mViewArray.get(Ids);
        if (view==null){
            view=itemView.findViewById(Ids);
            mViewArray.put(Ids,view);
        }
        return (T) view;
    }
}
