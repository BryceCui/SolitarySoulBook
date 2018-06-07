package com.cuipengyu.solitarysoulbook.entity.bean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class EvenBusEntityBook {
    private String bookName;
    private String bookUrl;
    private int type;

    public EvenBusEntityBook(String bookName) {
        this.bookName = bookName;
    }

    public EvenBusEntityBook(String bookName, String bookUrl) {
        this.bookName = bookName;
        this.bookUrl = bookUrl;
    }

    public EvenBusEntityBook(String bookName, String bookUrl, int type) {
        this.bookName = bookName;
        this.bookUrl = bookUrl;
        this.type = type;
    }
    public EvenBusEntityBook(String bookName, int type) {
        this.bookName = bookName;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getBookUrl() {
        return bookUrl == null ? "" : bookUrl;
    }

    public String getBookName() {
        return bookName == null ? "" : bookName;
    }

}
