package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.features.factsfeed.WriteFactCommand;
import com.revents.chronolog.model.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context appContext;

    public AppModule(Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    public FactWriter provideFactWriter() {
        throw new UnsupportedOperationException();
    }

    @Provides
    @Singleton
    public FactBuilder provideFactBuilder() {
        throw new UnsupportedOperationException();
    }

    @Provides
    @Singleton
    public Command provideWriteFactCommand(FactWriter factWriter, FactBuilder factBuilder) {
        return new WriteFactCommand(factWriter, factBuilder);
    }
}

