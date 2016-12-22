package com.revents.chronolog.app;

import android.app.Application;

import com.revents.chronolog.model.DaoMaster;
import com.revents.chronolog.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class ChronologApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "chronolog-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
