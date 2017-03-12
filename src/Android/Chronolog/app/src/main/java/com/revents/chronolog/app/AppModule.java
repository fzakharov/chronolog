package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.db.greendao.GreenDaoFactReader;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.feed.AddFactUiCommand;
import com.revents.chronolog.features.feed.EditFactActivity;
import com.revents.chronolog.features.feed.EditFactActivityCommand;
import com.revents.chronolog.features.feed.EditFactActivityExtractor;
import com.revents.chronolog.features.group.FactTypeGroupIntentExtractor;
import com.revents.chronolog.features.group.NewFactTypeGroupResultUiCommand;
import com.revents.chronolog.features.group.SelectFactTypeGroupResultUiCommand;
import com.revents.chronolog.features.statistics.ActiveWidgetsProvider;
import com.revents.chronolog.features.statistics.ShowStatUiAction;
import com.revents.chronolog.features.statistics.StatRecyclerViewItemProvider;
import com.revents.chronolog.features.statistics.StatWidgetsRecyclerViewAdapterFactory;
import com.revents.chronolog.features.statistics.Widget;
import com.revents.chronolog.features.statistics.WidgetFactory;
import com.revents.chronolog.features.statistics.WidgetRvViewHolder;
import com.revents.chronolog.features.statistics.WidgetsProvider;
import com.revents.chronolog.features.statistics.WidgetsRegistry;
import com.revents.chronolog.features.type.FactTypeIntentExtractor;
import com.revents.chronolog.features.type.NewFactTypeResultUiCommand;
import com.revents.chronolog.features.value.HardCodedValueTypesProvider;
import com.revents.chronolog.features.value.NewValueDescriptorResultUiCommand;
import com.revents.chronolog.features.value.SelectValueDescriptorResultUiCommand;
import com.revents.chronolog.features.value.ValueDescriptorIntentExtractor;
import com.revents.chronolog.features.value.ValueTypesProvider;
import com.revents.chronolog.model.DaoMaster;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;
import com.revents.chronolog.ui.UiAction;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

import org.greenrobot.greendao.database.Database;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

// TODO: 04.02.2017 extract submodules
@Module
public class AppModule {

    private DaoSession mDaoSession;
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
    public YesNoDialog provideYesNoDialog() {
        return new AlertYesNoDialog();
    }

    @Provides
    @Singleton
    public DaoSession provideDaoSession(Context context) {
        if (mDaoSession == null) // TODO: 14.01.2017 write test
        {
            ChronologDbOpenHelper helper = new ChronologDbOpenHelper(context, "chronolog-db");
            Database db = helper.getWritableDb();
            mDaoSession = new DaoMaster(db).newSession();
        }

        return mDaoSession;
    }

    @Provides
    @Singleton
    public RecyclerViewItemProvider<Widget, WidgetRvViewHolder> provideRecyclerViewItemProvider() {
        return new StatRecyclerViewItemProvider();
    }

    @Provides
    @Singleton
    public WidgetFactory<FactType> provideFactTypeWidgetFactory() {
        throw new UnsupportedOperationException();
    }

    @Provides
    @Singleton
    public WidgetsRegistry<FactType> provideFactTypeWidgetsRegistry() {
        throw new UnsupportedOperationException();
    }

    @Provides
    @Singleton
    public WidgetsProvider<FactType> provideFactTypeActiveWidgetsProvider(WidgetFactory<FactType> widgetFactory, WidgetsRegistry<FactType> widgetsRegistry) {
        return new ActiveWidgetsProvider<>(widgetFactory, widgetsRegistry);
    }

    @Provides
    @Singleton
    public RecyclerViewAdapterFactory<FactType> provideRecyclerViewAdapterFactoryForFactType(
            WidgetsProvider<FactType> widgetsListProvider,
            RecyclerViewItemProvider<Widget, WidgetRvViewHolder> widgetsRecyclerViewItemProvider) {

        return new StatWidgetsRecyclerViewAdapterFactory(
                widgetsListProvider,
                widgetsRecyclerViewItemProvider);
    }

    @Provides
    @Singleton
    public FactWriter provideFactWriter(DateTimeProvider dateTimeProvider, DaoSession session) {
        return new GreenDaoFactWriter(dateTimeProvider, session);
    }

    @Provides
    public DateDialog provideDateDialog() {
        return new AndroidDateDialog();
    }

    @Provides
    public TimeDialog provideTimeDialog() {
        return new AndroidTimeDialog();
    }

    @Provides
    @Singleton
    public AppCreateListener provideAppCreateListener(FactWriter factWriter) {
        return new DefaultAppCreateListener(factWriter);
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
    @Named(CommandTypes.SELECT)
    public UiCommand provideSelectFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        return new AddFactUiCommand(intentFactory, factReader);
    }

    @Provides
    @Singleton
    public UiAction<Fact> provideShowStatUiAction(IntentFactory intentFactory) {
        return new ShowStatUiAction(intentFactory);
    }

    @Provides
    @Singleton
    public ActivityExtractor<Fact, EditFactActivity> provideEditFactActivityExtractor() {
        return new EditFactActivityExtractor();
    }

    @Provides
    @Singleton
    public EditFactActivityCommand provideEditFactActivityCommand() {
        return new EditFactActivityCommand();
    }

    @Provides
    @Singleton
    public ResultUiCommand<FactType> provideNewFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        return new NewFactTypeResultUiCommand(intentFactory, factReader);
    }

    @Provides
    @Singleton
    public IntentExtractor<FactTypeGroup> provideIntentExtractorFactTypeGroup(FactReader factReader) {
        return new FactTypeGroupIntentExtractor(factReader);
    }

    @Provides
    @Singleton
    public IntentExtractor<FactType> provideIntentExtractorFactType(FactReader factReader) {
        return new FactTypeIntentExtractor(factReader);
    }

    @Provides
    @Singleton
    public IntentExtractor<ValueDescriptor> provideIntentExtractorValueDescriptor(FactReader factReader) {
        return new ValueDescriptorIntentExtractor(factReader);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.SELECT)
    public ResultUiCommand<FactTypeGroup> provideSelectFactTypeGroupActivityCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        return new SelectFactTypeGroupResultUiCommand(intentFactory, extractor);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.NEW)
    public ResultUiCommand<FactTypeGroup> provideNewFactTypeGroupUiCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        return new NewFactTypeGroupResultUiCommand(intentFactory, extractor);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.SELECT)
    public ResultUiCommand<ValueDescriptor> provideSelectValueDescriptorActivityCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> intentExtractor) {
        return new SelectValueDescriptorResultUiCommand(intentFactory, intentExtractor);
    }

    @Provides
    @Singleton
    @Named(CommandTypes.NEW)
    public ResultUiCommand<ValueDescriptor> provideNewValueDescriptorUiCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> intentExtractor) {
        return new NewValueDescriptorResultUiCommand(intentFactory, intentExtractor);
    }

    @Provides
    @Singleton
    public ValueTypesProvider provideValueTypesProvider() {
        return new HardCodedValueTypesProvider();
    }
}

