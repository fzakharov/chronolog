package com.revents.chronolog.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.revents.chronolog.model.DaoMaster;
import com.revents.chronolog.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class ChronologApp extends Application {
    private DaoSession daoSession;
    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "chronolog-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        initComponent();
    }

    public AppComponent getAppComponent() {
        return mComponent;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    protected void initComponent()
    {
        mComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule()) // This also corresponds to the name of your module: %component_name%Module
                .build();
    }

    @NonNull
    protected AppModule getAppModule() {
        return new AppModule(this);
    }
}


