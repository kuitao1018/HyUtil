package com.hongyan.hyutil;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.greendao.gen.DaoMaster;
import com.greendao.gen.Db_ShopDao;

import org.greenrobot.greendao.database.Database;

/**
 * @created: by Android Studio.
 * @author: hongyan.tao
 * @date: 2020/8/28
 * @describe: MySQLiteOpenHelper
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //需依赖数据库升级辅助类
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, Db_ShopDao.class);
    }
}
