package com.cuipengyu.solitarysoulbook.entity.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/29
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 历史记录
 */
@Entity
public class UserHisitoryBean {
    @Id
    private Long id;
    private Long hisitoryid;
    private String name;
    private String bookUrl;
    @Transient
    private int Type=0x001;

    public int getType() {
        return Type;
    }

    @Generated(hash = 2096388132)
    public UserHisitoryBean(Long id, Long hisitoryid, String name, String bookUrl) {
        this.id = id;
        this.hisitoryid = hisitoryid;
        this.name = name;
        this.bookUrl = bookUrl;
    }
    @Generated(hash = 2018376186)
    public UserHisitoryBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getHisitoryid() {
        return this.hisitoryid;
    }
    public void setHisitoryid(Long hisitoryid) {
        this.hisitoryid = hisitoryid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBookUrl() {
        return this.bookUrl;
    }
    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
