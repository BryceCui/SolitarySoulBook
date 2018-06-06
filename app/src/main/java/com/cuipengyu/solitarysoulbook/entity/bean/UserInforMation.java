package com.cuipengyu.solitarysoulbook.entity.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/29
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 个人信息
 */
@Entity
public class UserInforMation {
    @Id
    private Long id;
    private String userName;
    private String userImage;
    private String signaTrue;
    @Generated(hash = 420880326)
    public UserInforMation(Long id, String userName, String userImage,
            String signaTrue) {
        this.id = id;
        this.userName = userName;
        this.userImage = userImage;
        this.signaTrue = signaTrue;
    }
    @Generated(hash = 668853041)
    public UserInforMation() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserImage() {
        return this.userImage;
    }
    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
    public String getSignaTrue() {
        return this.signaTrue;
    }
    public void setSignaTrue(String signaTrue) {
        this.signaTrue = signaTrue;
    }
 
}
