package com.revents.chronolog.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    private Context appContext;

    public AppModule(Context context)
    {
        appContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext()
    {
        return appContext;
    }

    @Provides
    @Singleton
    public Command provideFactCReator()
    {
        return new ManualAddFactCommand();
    }
}

