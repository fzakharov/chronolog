package com.revents.chronolog.features.value;

import android.support.design.widget.FloatingActionButton;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.model.ValueDescriptor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ValueDescriptorsActivityRoboTests extends ActivityRoboTestsBase<ValueDescriptorsActivity> {

    ResultUiCommand<ValueDescriptor> mAddValueDescriptorUiCommand;

    @Before
    public void setUp() throws Exception {

        mAddValueDescriptorUiCommand = (ResultUiCommand<ValueDescriptor>) mock(ResultResultUiCommand.class);

        sutBuilder = Robolectric.buildActivity(ValueDescriptorsActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
    }

    @Test
    public void should_execute_addFactCommand_When_addFactFab_clicked() {
        // Given
        FloatingActionButton addBtn = (FloatingActionButton) viewById(R.id.addValueDescriptorFab);

        // When
        addBtn.performClick();

        // Then
        verify(mAddValueDescriptorUiCommand).execute(sut);
    }

    // TODO: 07.01.2017 call command when addFab clicked
    // TODO: 07.01.2017 click item test

    private void inject(final ValueDescriptorsActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(mAddValueDescriptorUiCommand);
                return null;
            }
        }).when(cmp).inject(sut);
    }
}
