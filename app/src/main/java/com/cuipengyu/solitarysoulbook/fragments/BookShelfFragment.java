package com.cuipengyu.solitarysoulbook.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.adapter.ShelfAdapter;
import com.cuipengyu.solitarysoulbook.base.AdapterDelegateManager;
import com.cuipengyu.solitarysoulbook.base.BaseFragment;
import com.cuipengyu.solitarysoulbook.base.BaseRvAdapter;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.UserShelfBean;
import com.cuipengyu.solitarysoulbook.utils.DbUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/4
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookShelfFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private RelativeLayout book_shelf_layout_recom;
    private BaseRvAdapter baseRvAdapter = null;
    private List<UserShelfBean> UserShelfBean;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_mian_tab_bookshelf;
    }

    @Override
    protected void initData() {
        UserShelfBean = DbUtils.getSession().getUserShelfBeanDao().loadAll();
        AdapterDelegateManager manager = new AdapterDelegateManager();
        manager.addDelegate(new ShelfAdapter(getActivity()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        baseRvAdapter = null;
        baseRvAdapter = new BaseRvAdapter(UserShelfBean, manager);
        mRecyclerView.setAdapter(baseRvAdapter);

    }

    @Override
    protected void initView() {
        mRecyclerView = findView(R.id.main_shelf_rv);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            UserShelfBean.clear();
            UserShelfBean = DbUtils.getSession().getUserShelfBeanDao().loadAll();
            baseRvAdapter.notifyDataSetChanged();
        }
    }
}
