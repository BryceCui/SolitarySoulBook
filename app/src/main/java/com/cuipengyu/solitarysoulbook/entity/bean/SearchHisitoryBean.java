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
public class SearchHisitoryBean {
    private Long id;
    private String name = "搜索历史";
    private List<String> searchName;
    public String getName() {
        return name == null ? "" : name;
    }

    public List<String> getSearchName() {
        if (searchName == null) return new ArrayList<>();
        return searchName;
    }

    public void setSearchName(List<String> searchName) {
        this.searchName = searchName;
    }
}
