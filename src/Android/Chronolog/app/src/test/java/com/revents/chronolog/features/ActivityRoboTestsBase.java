package com.revents.chronolog.features;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.*;
import com.revents.chronolog.features.feed.FactsfeedActivity;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

// TODO: 06.01.2017 use as base in other robo tests
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public abstract class ActivityRoboTestsBase<T extends Activity> {
    protected T sut;
    protected ActivityController<T> sutBuilder;

    protected void should_execute_When_click_test_case(@IdRes int id, com.revents.chronolog.app.ResultUiCommand command) {
        // Given
        Button btn = viewById(id);

        // When
        btn.performClick();

        // Then
        verify(command).execute(sut);
    }

    protected <TView extends View> TView viewById(@IdRes int id) {
        return (TView) sut.findViewById(id);
    }

    protected RecyclerView getMesuredRv(int id){
        RecyclerView rv = viewById(id);
        rv.measure(0, 0);
        rv.layout(0, 0, 100, 1000);

        return rv;
    }
}
