package com.revents.chronolog.features.type;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.FakeChronologApp;
import com.revents.chronolog.features.type.FactTypesActivity;
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

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class FactTypesActivityRoboTests {
    private FactTypesActivity sut;
    private ActivityController<FactTypesActivity> sutBuilder;
    private UiCommand<FactType> mAddFactTypeUiCommand;

    @Before
    public void setUp() throws Exception {

        mAddFactTypeUiCommand = (UiCommand<FactType>) mock(UiCommand.class);

        sutBuilder = Robolectric.buildActivity(FactTypesActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
    }

    @Test
    public void should_call_addFactTypeActivityCommand_onResult_When_onActivityResult() {
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
        verify(mAddFactTypeUiCommand).onResult(sut, requestCode, resultCode, resultIntent);
    }

    @Test
    public void should_execute_addFactTypeCommand_When_addFactTypeFab_clicked() {
        // Given
        FloatingActionButton addBtn = viewById(R.id.addFactTypeFab);

        // When
        addBtn.performClick();

        // Then
        verify(mAddFactTypeUiCommand).execute(sut);
    }

    private <T> T viewById(@IdRes int id) {
        return (T) sut.findViewById(id);
    }

    private void inject(final FactTypesActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(mAddFactTypeUiCommand);
                return null;
            }
        }).when(cmp).inject(sut);
    }
}
