package com.revents.chronolog.features.statistics;

import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.features.ActivityRoboTestsBase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import static org.mockito.Mockito.doAnswer;

public class FactTypeStatisticsActivityRoboTests  extends ActivityRoboTestsBase<FactTypeStatisticsActivity> {

    @Before
    public void setUp() throws Exception {

        sutBuilder = Robolectric.buildActivity(FactTypeStatisticsActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
    }

    @Test
    public void should__When_() {
        // Given

        // When

        // Then
        throw new UnsupportedOperationException();
    }

    private void inject(final FactTypeStatisticsActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(invocation -> {

            activity.inject();
            return null;
        }).when(cmp).inject(sut);
    }
}
