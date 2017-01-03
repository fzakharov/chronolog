package com.revents.chronolog.features;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.app.FakeChronologApp;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public abstract class ActivityRoboTestsBase<T extends Activity> {
    protected T sut;
    protected ActivityController<T> sutBuilder;

    protected void should_execute_When_click_test_case(@IdRes int id, ActivityCommand command)
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
