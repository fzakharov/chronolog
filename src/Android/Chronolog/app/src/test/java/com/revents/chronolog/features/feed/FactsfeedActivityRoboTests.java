package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.FakeChronologApp;
import com.revents.chronolog.features.feed.FactsfeedActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class FactsfeedActivityRoboTests {

    private UiCommand addFactUiCommand;
    private FactsfeedActivity sut;
    private ActivityController<FactsfeedActivity> sutBuilder;

    @Before
    public void setUp() throws Exception {
        addFactUiCommand = mock(UiCommand.class);

        sutBuilder = Robolectric.buildActivity(FactsfeedActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
    }

    private void inject(final FactsfeedActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(addFactUiCommand);
                return null;
            }
        }).when(cmp).inject(sut);
    }

    @Test
    public void should_call_addFactActivityCommand_onResult_When_onActivityResult() {
        // Given
        Class<Activity> activityClass = Activity.class;
        int requestCode = 42;
        int resultCode = 33;
        Intent resultIntent = new Intent();

        sut.startActivityForResult(new Intent(sut, activityClass), requestCode);

        // When
        shadowOf(sut).receiveResult(
                new Intent(sut, activityClass),
                resultCode,
                resultIntent);

        // Then
        verify(addFactUiCommand).onResult(sut, requestCode, resultCode, resultIntent);
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
        verify(addFactUiCommand).execute(sut);
    }

    private <T> T viewById(@IdRes int id) {
        return (T) sut.findViewById(id);
    }
}
