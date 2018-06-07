package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;
import com.cuipengyu.solitarysoulbook.entity.bean.UserBean;
import com.cuipengyu.solitarysoulbook.entity.bean.UserHisitoryBean;
import com.cuipengyu.solitarysoulbook.entity.db.UserBeanDao;
import com.cuipengyu.solitarysoulbook.utils.DbUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/24
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： listpopupWindow 适配器
 */
public class SearchAutomaticAdapter extends AdapterDelegate<SearchViewBean> {

    @Override
    protected boolean isForViewType(SearchViewBean itmes, int position) {
        return itmes.getAutomaticBean() != null;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_rv_automatic, parent, false));

    }

    @Override
    protected void onBindViewHolder(final SearchViewBean itmes, final int position, BaseViewHolder holder) {
        holder.setText(R.id.automatic_tv, itmes.getAutomaticBean().getKeywords().get(position));
        holder.setOnClickListener(R.id.automatic_tv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean = DbUtils.getSession().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Name.eq(Constants.USER_NAME)).unique();
                UserHisitoryBean userHisitoryBean = new UserHisitoryBean();
                userHisitoryBean.setHisitoryid(userBean.getId());
                userHisitoryBean.setName(itmes.getAutomaticBean().getKeywords().get(position));
                DbUtils.getSession().getUserHisitoryBeanDao().insertOrReplace(userHisitoryBean);
                EventBus.getDefault().post(new EvenBusEntityBook(itmes.getAutomaticBean().getKeywords().get(position),0));
            }
        });
    }

    @Override
    protected int ItemCount(SearchViewBean items) {
        return items.getAutomaticBean() != null ? items.getAutomaticBean().getKeywords().size() : 0;
    }
}
