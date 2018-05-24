package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/24
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： listpopupWindow 适配器
 */
public class SearchAutomaticAdapter extends BaseAdapter {
    private AutomaticBean mData;

    public SearchAutomaticAdapter(AutomaticBean automaticBean) {
        this.mData = automaticBean;
    }

    @Override
    public int getCount() {
        return mData==null?0:mData.getKeywords().size();
    }

    @Override
    public Object getItem(int position) {
        return mData.getKeywords().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PopupViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.searchview_popupwindow_item, null);
            viewHolder = new PopupViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PopupViewHolder) convertView.getTag();
        }
        viewHolder.search_popup_item_tv.setText(mData.getKeywords().get(position));
        return convertView;
    }

    class PopupViewHolder {
        TextView search_popup_item_tv;

        PopupViewHolder(View rootView) {
            search_popup_item_tv = (TextView) rootView.findViewById(R.id.search_popup_item_tv);
        }

    }
}
