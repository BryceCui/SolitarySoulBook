package com.cuipengyu.solitarysoulbook.entity.bean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class SearchViewBean {

    private HotWord mHotWord;
    private SearchHisitoryBean mHisitoryBean;

    public HotWord getHotWord() {
        return mHotWord;
    }

    public void setHotWord(HotWord hotWord) {
        mHotWord = hotWord;
    }

    public SearchHisitoryBean getHisitoryBean() {
        return mHisitoryBean;
    }

    public void setHisitoryBean(SearchHisitoryBean hisitoryBean) {
        mHisitoryBean = hisitoryBean;
    }
}
