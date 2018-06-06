package com.cuipengyu.solitarysoulbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegate;
import com.cuipengyu.solitarysoulbook.base.BaseViewHolder;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.bean.SearchViewBean;
import com.cuipengyu.solitarysoulbook.entity.bean.UserBean;
import com.cuipengyu.solitarysoulbook.entity.bean.UserHisitoryBean;
import com.cuipengyu.solitarysoulbook.entity.db.UserBeanDao;
import com.cuipengyu.solitarysoulbook.utils.DbUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchHotWordAdapter extends AdapterDelegate<SearchViewBean> {
    View view;

    @Override
    protected boolean isForViewType(SearchViewBean itmes, int position) {
        if (itmes.getHotWord() != null) {
            return position < itmes.getHotWord().getHotSize() + 1;
        }
        return false;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_hotwoed_rv_item, parent, false);
        return new HotWordHolder(view);
    }

    @Override
    protected void onBindViewHolder(final SearchViewBean itmes, final int position, BaseViewHolder holder) {
        holder.setText(R.id.search_tv, itmes.getHotWord().getHotWords().get(position - 1));
        holder.setOnClickListener(R.id.search_tv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean = DbUtils.getSession().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Name.eq(Constants.USER_NAME)).unique();
                UserHisitoryBean userHisitoryBean = new UserHisitoryBean();
                userHisitoryBean.setHisitoryid(userBean.getId());
                userHisitoryBean.setName(itmes.getHotWord().getHotWords().get(position - 1));
                userHisitoryBean.setBookUrl(itmes.getHotWord().getNewHotWords().get(position-1).getBook());
                DbUtils.getSession().getUserHisitoryBeanDao().insertOrReplace(userHisitoryBean);
                EventBus.getDefault().post(new EvenBusEntityBook(itmes.getHotWord().getHotWords().get(position - 1),itmes.getHotWord().getNewHotWords().get(position-1).getBook()));
            }
        });
    }

    @Override
    protected int ItemCount(SearchViewBean item) {
        return item.getHotWord() == null ? 0 : item.getHotWord().getHotSize();
    }

    class HotWordHolder extends BaseViewHolder {

        public HotWordHolder(View itemView) {
            super(itemView);
        }
    }
}
