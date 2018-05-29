package com.cuipengyu.solitarysoulbook.entity.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.cuipengyu.solitarysoulbook.entity.db.DaoSession;
import com.cuipengyu.solitarysoulbook.entity.db.UserShelfBeanDao;
import com.cuipengyu.solitarysoulbook.entity.db.UserInforMationDao;
import com.cuipengyu.solitarysoulbook.entity.db.UserHisitoryBeanDao;
import com.cuipengyu.solitarysoulbook.entity.db.UserBeanDao;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/29
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 用户账号密码
 */
@Entity
public class UserBean {
    @Id
    private Long id;
    @NotNull
    @Unique
    private String name;
    @NotNull
    private String passWord;
    @ToMany(referencedJoinProperty = "hisitoryid")
    private List<UserHisitoryBean> userHisitoryBeans;
    @ToMany(referencedJoinProperty = "informationid")
    private List<UserInforMation> userInforMations;
    @ToMany(referencedJoinProperty = "shelfid")
    private List<UserShelfBean> userShelfBeans;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 83707551)
    private transient UserBeanDao myDao;
    @Generated(hash = 1175392014)
    public UserBean(Long id, @NotNull String name, @NotNull String passWord) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 170068066)
    public List<UserHisitoryBean> getUserHisitoryBeans() {
        if (userHisitoryBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserHisitoryBeanDao targetDao = daoSession.getUserHisitoryBeanDao();
            List<UserHisitoryBean> userHisitoryBeansNew = targetDao
                    ._queryUserBean_UserHisitoryBeans(id);
            synchronized (this) {
                if (userHisitoryBeans == null) {
                    userHisitoryBeans = userHisitoryBeansNew;
                }
            }
        }
        return userHisitoryBeans;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1887793806)
    public synchronized void resetUserHisitoryBeans() {
        userHisitoryBeans = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 185649761)
    public List<UserInforMation> getUserInforMations() {
        if (userInforMations == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserInforMationDao targetDao = daoSession.getUserInforMationDao();
            List<UserInforMation> userInforMationsNew = targetDao
                    ._queryUserBean_UserInforMations(id);
            synchronized (this) {
                if (userInforMations == null) {
                    userInforMations = userInforMationsNew;
                }
            }
        }
        return userInforMations;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 95418115)
    public synchronized void resetUserInforMations() {
        userInforMations = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 870195882)
    public List<UserShelfBean> getUserShelfBeans() {
        if (userShelfBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserShelfBeanDao targetDao = daoSession.getUserShelfBeanDao();
            List<UserShelfBean> userShelfBeansNew = targetDao
                    ._queryUserBean_UserShelfBeans(id);
            synchronized (this) {
                if (userShelfBeans == null) {
                    userShelfBeans = userShelfBeansNew;
                }
            }
        }
        return userShelfBeans;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 208929381)
    public synchronized void resetUserShelfBeans() {
        userShelfBeans = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1491512534)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserBeanDao() : null;
    }
}
