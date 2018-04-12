package com.cuipengyu.solitarysoulbook.entity.bean;

import com.cuipengyu.solitarysoulbook.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/11
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 精选
 */
public class SelectBean extends BaseBean {
    private List<SelectBook> selectBooks_List;
    public List<SelectBook> getSelectBooks_List() {
        if (selectBooks_List == null) return new ArrayList<>();
        return selectBooks_List;
    }

    public void setSelectBooks_List(List<SelectBook> selectBooks_List) {
        this.selectBooks_List = selectBooks_List;
    }

    public static class SelectBook {
        private String img_link;//图片链接
        private String name; //书名
        private String book_Id;
        private String author_Statua;//作者和状态
        private String Introduction; //简介

        public String getImg_link() {
            return img_link == null ? "" : img_link;
        }

        public void setImg_link(String img_link) {
            this.img_link = img_link;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBook_Id() {
            return book_Id == null ? "" : book_Id;
        }

        public void setBook_Id(String book_Id) {
            this.book_Id = book_Id;
        }

        public String getAuthor_Statua() {
            return author_Statua == null ? "" : author_Statua;
        }

        public void setAuthor_Statua(String author_Statua) {
            this.author_Statua = author_Statua;
        }

        public String getIntroduction() {
            return Introduction == null ? "" : Introduction;
        }

        public void setIntroduction(String introduction) {
            Introduction = introduction;
        }
    }
}
