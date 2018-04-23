package com.cuipengyu.solitarysoulbook.entity.bean;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/22
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BaseIsOk <T>{

    private boolean ok;
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
