package com.revents.chronolog.features.value;

import android.widget.ListView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.HeadedLvItem;
import com.revents.chronolog.model.ValueDescriptor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValueDescriptorsActivityRoboTests extends ActivityRoboTestsBase<ValueDescriptorsActivity> {

    UiCommand<ValueDescriptor> mAddValueDescriptorUiCommand;

    @Before
    public void setUp() throws Exception {

        mAddValueDescriptorUiCommand = (UiCommand<ValueDescriptor>) mock(UiCommand.class);

        sutBuilder = Robolectric.buildActivity(ValueDescriptorsActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
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
