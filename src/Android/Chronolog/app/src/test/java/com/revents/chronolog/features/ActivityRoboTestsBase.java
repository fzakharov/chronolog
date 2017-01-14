package com.revents.chronolog.features;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.app.*;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.mockito.Mockito.verify;

// TODO: 06.01.2017 use as base in other robo tests
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public abstract class ActivityRoboTestsBase<T extends Activity> {
    protected T sut;
    protected ActivityController<T> sutBuilder;

    protected void should_execute_When_click_test_case(@IdRes int id, com.revents.chronolog.app.ResultUiCommand command)
    {
        // Given
        Button btn = (Button) viewById(id);

        // When
        btn.performClick();

        // Then
        verify(command).execute(sut);
    }

    protected View viewById(@IdRes int id) {
        return sut.findViewById(id);
    }
}
