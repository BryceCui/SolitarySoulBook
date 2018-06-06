package com.cuipengyu.solitarysoulbook.entity.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCityBean {
    List<String> strings;
    BookCityRecommendBean recommendBean;
    BookCitySpread citySpread;

    public List<String> getStrings() {
        if (strings == null) return new ArrayList<>();
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public BookCityRecommendBean getRecommendBean() {
        return recommendBean;
    }

    public void setRecommendBean(BookCityRecommendBean recommendBean) {
        this.recommendBean = recommendBean;
    }

    public BookCitySpread getCitySpread() {
        return citySpread;
    }

    public void setCitySpread(BookCitySpread citySpread) {
        this.citySpread = citySpread;
    }
}
