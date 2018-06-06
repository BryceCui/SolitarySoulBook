package com.cuipengyu.solitarysoulbook.entity.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchViewBean {

    private HotWord mHotWord;
    private List<UserHisitoryBean> mHisitoryBean;
    private AutomaticBean automaticBean;

    public AutomaticBean getAutomaticBean() {
        return automaticBean;
    }

    public void setAutomaticBean(AutomaticBean automaticBean) {
        this.automaticBean = automaticBean;
    }

    public HotWord getHotWord() {
        return mHotWord;
    }

    public void setHotWord(HotWord hotWord) {
        mHotWord = hotWord;
    }

    public HotWord getmHotWord() {
        return mHotWord;
    }

    public void setmHotWord(HotWord mHotWord) {
        this.mHotWord = mHotWord;
    }

    public List<UserHisitoryBean> getmHisitoryBean() {
        if (mHisitoryBean == null) return new ArrayList<>();
        return mHisitoryBean;
    }

    public void setmHisitoryBean(List<UserHisitoryBean> mHisitoryBean) {
        this.mHisitoryBean = mHisitoryBean;
    }
}
