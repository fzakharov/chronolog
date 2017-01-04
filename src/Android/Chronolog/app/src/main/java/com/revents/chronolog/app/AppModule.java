package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.db.greendao.GreenDaoFactReader;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.features.EditFactActivityCommand;
import com.revents.chronolog.features.FactTypeGroupIntentExtractor;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.NewFactUiCommand;
import com.revents.chronolog.features.NewFactTypeUiCommand;
import com.revents.chronolog.features.SelectFactTypeUiCommand;
import com.revents.chronolog.features.SelectFactTypeGroupUiCommand;
import com.revents.chronolog.features.SelectValueDescriptorUiCommand;
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
    public SelectFactTypeUiCommand provideSelectFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        return new SelectFactTypeUiCommand(intentFactory, factReader);
    }

    @Provides
    @Singleton
    public EditFactActivityCommand provideEditFactActivityCommand() {
        return new EditFactActivityCommand();
    }

    @Provides
    @Singleton
    public UiCommand<Fact> provideNewFactActivityCommand(SelectFactTypeUiCommand selectFactTypeCommand, EditFactActivityCommand editFactCommand) {
        return new NewFactUiCommand(selectFactTypeCommand, editFactCommand);
    }

    @Provides
    @Singleton
    public UiCommand<FactType> provideNewFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        return new NewFactTypeUiCommand(intentFactory, factReader);
    }

    @Provides
    @Singleton
    public IntentExtractor<FactTypeGroup> provideIntentExtractorFactTypeGroup(FactReader factReader) {
        return new FactTypeGroupIntentExtractor(factReader);
    }

    @Provides
    @Singleton
    public UiCommand<FactTypeGroup> provideSelectFactTypeGroupActivityCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        return new SelectFactTypeGroupUiCommand(intentFactory, extractor);
    }

    @Provides
    @Singleton
    public UiCommand<ValueDescriptor> provideSelectValueDescriptorActivityCommand() {
        return new SelectValueDescriptorUiCommand();
    }
}

