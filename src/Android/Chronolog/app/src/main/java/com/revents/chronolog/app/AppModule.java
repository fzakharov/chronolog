package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.db.greendao.GreenDaoFactReader;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.features.feed.EditFactActivityCommand;
import com.revents.chronolog.features.group.FactTypeGroupIntentExtractor;
import com.revents.chronolog.features.value.HardCodedValueTypesProvider;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.group.NewFactTypeGroupUiCommand;
import com.revents.chronolog.features.feed.NewFactUiCommand;
import com.revents.chronolog.features.type.NewFactTypeUiCommand;
import com.revents.chronolog.features.value.NewValueDescriptorUiCommand;
import com.revents.chronolog.features.type.SelectFactTypeUiCommand;
import com.revents.chronolog.features.group.SelectFactTypeGroupUiCommand;
import com.revents.chronolog.features.value.SelectValueDescriptorUiCommand;
import com.revents.chronolog.features.value.ValueDescriptorIntentExtractor;
import com.revents.chronolog.features.value.ValueTypesProvider;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

import javax.inject.Named;
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
        // TODO: 26.12.2016 move db init and session
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
    public IntentExtractor<ValueDescriptor> provideIntentExtractorValueDescriptor(FactReader factReader) {
        return new ValueDescriptorIntentExtractor(factReader);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.SELECT)
    public UiCommand<FactTypeGroup> provideSelectFactTypeGroupActivityCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        return new SelectFactTypeGroupUiCommand(intentFactory, extractor);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.NEW)
    public UiCommand<FactTypeGroup> provideNewFactTypeGroupUiCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        return new NewFactTypeGroupUiCommand(intentFactory, extractor);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.SELECT)
    public UiCommand<ValueDescriptor> provideSelectValueDescriptorActivityCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> intentExtractor) {
        return new SelectValueDescriptorUiCommand(intentFactory, intentExtractor);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.NEW)
    public UiCommand<ValueDescriptor> provideNewValueDescriptorUiCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> intentExtractor) {
        return new NewValueDescriptorUiCommand(intentFactory, intentExtractor);
    }

    @Provides
    @Singleton
    public ValueTypesProvider provideValueTypesProvider()
    {
        return new HardCodedValueTypesProvider();
    }
}

