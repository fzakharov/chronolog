package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.NewFactActivityCommand;
import com.revents.chronolog.features.SelectFactTypeActivityCommand;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private ChronologApp appContext;

    public AppModule(Context context) {
        appContext = (ChronologApp) context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    public DateTimeProvider provideDateTimeProvider() {
        return new JavaDateTimeProvider();
    }

    @Provides
    @Singleton
    public DaoSession provideDaoSession() {
        // TODO: 26.12.2016 move here db init and session
        return appContext.getDaoSession();
    }

    @Provides
    @Singleton
    public FactWriter provideFactWriter(DateTimeProvider dateTimeProvider, DaoSession session) {
        return new GreenDaoFactWriter(dateTimeProvider, session);
    }

    @Provides
    @Singleton
    public IntentFactory provideIntentFactory() {
        return new DefaultIntentFactory();
    }

    @Provides
    @Singleton
    public ActivityCommand<FactType> provideSelectFactTypeActivityCommand(IntentFactory intentFactory) {
        return new SelectFactTypeActivityCommand(intentFactory);
    }

    @Provides
    @Singleton
    public ActivityCommand<Fact> provideNewFactActivityCommand(ActivityCommand<FactType> selectFactTypeCommand) {
        return new NewFactActivityCommand(selectFactTypeCommand);
    }
}

