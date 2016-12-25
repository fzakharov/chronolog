package com.revents.chronolog.app;

import android.content.Context;
import android.support.annotation.NonNull;

public class TestChronologApp extends ChronologApp
{
    private AppModule appModule;

    @NonNull
    @Override
    protected AppModule getAppModule() {
        if (appModule == null)
            appModule = super.getAppModule();

        return appModule;
    }

    public void setAppModule(AppModule appModule)
    {
        this.appModule = appModule;
        initComponent();
    }
}
