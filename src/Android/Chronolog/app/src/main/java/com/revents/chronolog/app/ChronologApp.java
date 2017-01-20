package com.revents.chronolog.app;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class ChronologApp extends Application {

    private AppComponent mComponent;
    private AppCreateListener mCreateListener;

    // TODO: 14.01.2017 On deploy functionality: add predefined fact types

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = buildAppComponent();
        mComponent.inject(this);

        mCreateListener.onCreate();
    }

    @Inject
    public void inject(AppCreateListener createListener) {
        mCreateListener = createListener;
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


