package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.FakeChronologApp;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class FactsfeedActivityRoboTests {

    private ResultUiCommand addFactResultUiCommand;
    private FactsfeedActivity sut;
    private ActivityController<FactsfeedActivity> sutBuilder;
    private FactReader mFactReader;

    @Before
    public void setUp() throws Exception {
        addFactResultUiCommand = mock(ResultUiCommand.class);
        mFactReader = mock(FactReader.class);

        sutBuilder = Robolectric.buildActivity(FactsfeedActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start();
    }

    private void inject(final FactsfeedActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(addFactResultUiCommand, mFactReader);
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
        verify(addFactResultUiCommand).onResult(sut, requestCode, resultCode, resultIntent);
    }

    @Test
    public void should_load_facts_When_resume() {
        // Given
        String expectedName = "coffee";
        Fact fact = new Fact(42L, null, new Date(), 1L, "", 1L);
        fact.setFactType(new FactType(1L, expectedName, "", false, 1L, 1L));
        List<Fact> list = new ArrayList<>();
        list.add(fact);

        when(mFactReader.loadFactsfeed())
                .thenReturn(list);

        // When
        sutBuilder.resume();

        RecyclerView rv = viewById(R.id.factsfeedRv);
        rv.measure(0, 0);
        rv.layout(0, 0, 100, 1000);
        TextView tv = (TextView) rv.findViewHolderForLayoutPosition(0).itemView.findViewById(R.id.headerTv);

        // Then
        assertEquals(expectedName, tv.getText());
    }

    @Test
    public void should_execute_addFactCommand_When_addFactFab_clicked() {
        // Given
        FloatingActionButton addBtn = viewById(R.id.addFactFab);

        // When
        addBtn.performClick();

        // Then
        verify(addFactResultUiCommand).execute(sut);
    }

    private <T> T viewById(@IdRes int id) {
        return (T) sut.findViewById(id);
    }
}
