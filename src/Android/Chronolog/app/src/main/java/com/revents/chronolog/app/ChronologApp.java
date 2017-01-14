package com.revents.chronolog.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.revents.chronolog.model.DaoMaster;
import com.revents.chronolog.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class ChronologApp extends Application {

    private AppComponent mComponent;

    // TODO: 14.01.2017 On deploy functionality: add predefined fact types

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = buildAppComponent();
    }



    public AppComponent getAppComponent() {
        return mComponent;
    }

    protected AppComponent buildAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(getAppModule()) // This also corresponds to the name of your module: %component_name%Module
                .build();
    }

    @NonNull
    protected AppModule getAppModule() {
        return new AppModule(this);
    }
}


