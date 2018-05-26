package com.cuipengyu.solitarysoulbook.entity.bean;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/24
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class AutomaticBean {

    /**
     * keywords : ["剑来","剑中仙","剑锋","剑徒之路","剑灵同居日记","剑游太墟","剑棕","剑道通神","剑圣崛起","剑道独尊"]
     * ok : true
     */

    private boolean ok;
    private List<String> keywords;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
