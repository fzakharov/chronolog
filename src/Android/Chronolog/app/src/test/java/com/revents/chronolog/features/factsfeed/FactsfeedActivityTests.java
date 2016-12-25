package com.revents.chronolog.features.factsfeed;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.AppModule;
import com.revents.chronolog.app.Command;
import com.revents.chronolog.app.DateTimeProvider;
import com.revents.chronolog.app.FactBuilder;
import com.revents.chronolog.app.TestChronologApp;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.DaoSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = TestChronologApp.class)
public class FactsfeedActivityTests {

    private Command addFactCommand;
    private FactsfeedActivity sut;

    @Before
    public void setUp() throws Exception {
        addFactCommand = mock(Command.class);

        TestChronologApp testApp = (TestChronologApp) RuntimeEnvironment.application;

        AppModule appModule = mock(AppModule.class);
        when(appModule.provideContext()).thenReturn(testApp);
        when(appModule.provideFactBuilder()).thenReturn(mock(FactBuilder.class));
        when(appModule.provideFactWriter(mock(DateTimeProvider.class), mock(DaoSession.class))).thenReturn(mock(FactWriter.class));
        when(appModule.provideWriteFactCommand(isA(FactWriter.class), isA(FactBuilder.class)))
                .thenReturn(addFactCommand);

        testApp.setAppModule(appModule);

        sut = Robolectric.buildActivity(FactsfeedActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void should_setAdapter_to_factsfeedListView_When_setAdapter() {
        // Given
        ListView lvFeed = viewById(R.id.factsfeedListView);
        ArrayAdapter<String> testAdapter = new ArrayAdapter<String>(sut, 0);

        // When
        sut.setAdapter(testAdapter);

        // Then
        assertEquals(testAdapter, lvFeed.getAdapter());
    }

    @Test
    public void should_execute_addFactCommand_When_addFactFab_clicked() {
        // Given
        FloatingActionButton addBtn = viewById(R.id.addFactFab);

        // When
        addBtn.performClick();

        // Then
        verify(addFactCommand).execute();
    }

    private <T> T viewById(@IdRes int id) {
        return (T) sut.findViewById(id);
    }
}
