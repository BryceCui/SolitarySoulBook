package com.cuipengyu.solitarysoulbook.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/9
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 适用于新闻列表的adapter
 */
public class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private AdapterDelegateManager<T> mManager;
    private T mData;

    public BaseRvAdapter(T data, @Nullable AdapterDelegateManager<T> manager) {
        this.mData = data;
        this.mManager = manager;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        mManager.onBindViewHolder(mData, position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return mManager.getItemViewType(mData, position);
    }

    @Override
    public int getItemCount() {
        return mManager.getItemCount(mData);
    }
    public T getData(){
        return mData;
    }

//    @Override
//    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
//        final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//            setSpanSize(gridManager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    int type = getItemViewType(position);
//                    /**
//                     * 对类型进行判断
//                     *  总共分成6份进行垂直排列
//                     * new GridLayoutManager(this,6,GridLayoutManager.VERTICAL,false)
//                     *  return 3 就是6/3=2 把itme分成2列
//                     *  return 1 就是6/1=6 把item分成6列
//                     *  gridManager.getSpanCount()就是获取设置的SpanCount也就是6 所以说就是铺满
//                     */
//                    switch (type) {
//                        setSpanSize(type);
//                        default:
//                            return gridManager.getSpanCount();
//                    }
//                }
//            });
//        }
//        super.onAttachedToRecyclerView(recyclerView);
//    }

}
