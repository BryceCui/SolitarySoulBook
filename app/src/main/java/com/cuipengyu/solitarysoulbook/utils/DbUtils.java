package com.cuipengyu.solitarysoulbook.utils;

import android.database.sqlite.SQLiteDatabase;

import com.cuipengyu.solitarysoulbook.entity.db.DaoMaster;
import com.cuipengyu.solitarysoulbook.entity.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/29
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class DbUtils {
    private static DaoMaster daoMaster;

    public static void initDb() {
        DaoMaster.OpenHelper openHelper = new DaoMaster.OpenHelper(ApplicationContextUtil.getAppConnect(), "test.db") {
            @Override
            public void onCreate(Database db) {
                super.onCreate(db);
            }
        };
        SQLiteDatabase database = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
    }

    public static DaoSession getSession() {
        return daoMaster.newSession();
    }

}
