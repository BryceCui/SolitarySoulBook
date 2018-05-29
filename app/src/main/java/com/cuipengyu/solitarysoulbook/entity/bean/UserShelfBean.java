package com.cuipengyu.solitarysoulbook.entity.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/29
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：存储书架数据
 *
 */
@Entity
public class UserShelfBean {
    @Id
    private Long id;
    private Long shelfid;
    private String bookName;
    private String bookImage;
    private String bookUrl;
    @Generated(hash = 1584832904)
    public UserShelfBean(Long id, Long shelfid, String bookName, String bookImage,
            String bookUrl) {
        this.id = id;
        this.shelfid = shelfid;
        this.bookName = bookName;
        this.bookImage = bookImage;
        this.bookUrl = bookUrl;
    }
    @Generated(hash = 175919019)
    public UserShelfBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getShelfid() {
        return this.shelfid;
    }
    public void setShelfid(Long shelfid) {
        this.shelfid = shelfid;
    }
    public String getBookName() {
        return this.bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookImage() {
        return this.bookImage;
    }
    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }
    public String getBookUrl() {
        return this.bookUrl;
    }
    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
