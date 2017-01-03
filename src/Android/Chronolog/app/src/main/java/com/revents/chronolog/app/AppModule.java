package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.db.greendao.GreenDaoFactReader;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.features.EditFactActivityCommand;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.NewFactActivityCommand;
import com.revents.chronolog.features.NewFactTypeActivityCommand;
import com.revents.chronolog.features.SelectFactTypeActivityCommand;
import com.revents.chronolog.features.SelectFactTypeGroupActivityCommand;
import com.revents.chronolog.features.SelectValueDescriptorActivityCommand;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

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
    public FactReader provideFactReader(DaoSession session) {
        return new GreenDaoFactReader(session);
    }

    @Provides
    @Singleton
    public IntentFactory provideIntentFactory() {
        return new DefaultIntentFactory();
    }

    @Provides
    @Singleton
    public SelectFactTypeActivityCommand provideSelectFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        return new SelectFactTypeActivityCommand(intentFactory, factReader);
    }

    @Provides
    @Singleton
    public EditFactActivityCommand provideEditFactActivityCommand() {
        return new EditFactActivityCommand();
    }

    @Provides
    @Singleton
    public ActivityCommand<Fact> provideNewFactActivityCommand(SelectFactTypeActivityCommand selectFactTypeCommand, EditFactActivityCommand editFactCommand) {
        return new NewFactActivityCommand(selectFactTypeCommand, editFactCommand);
    }

    @Provides
    @Singleton
    public ActivityCommand<FactType> provideNewFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        return new NewFactTypeActivityCommand(intentFactory, factReader);
    }

    @Provides
    @Singleton
    public ActivityCommand<FactTypeGroup> provideSelectFactTypeGroupActivityCommand() {
        return new SelectFactTypeGroupActivityCommand();
    }

    @Provides
    @Singleton
    public ActivityCommand<ValueDescriptor> provideSelectValueDescriptorActivityCommand() {
        return new SelectValueDescriptorActivityCommand();
    }
}

