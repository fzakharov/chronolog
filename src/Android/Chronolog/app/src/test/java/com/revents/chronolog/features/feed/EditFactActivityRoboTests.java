package com.revents.chronolog.features.feed;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.FakeChronologApp;

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

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class EditFactActivityRoboTests {

    private EditFactActivity sut;
    private ActivityController<EditFactActivity> sutBuilder;

    @Before
    public void setUp() throws Exception {

        sutBuilder = Robolectric.buildActivity(EditFactActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
    }

    private void inject(final EditFactActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject();
                return null;
            }
        }).when(cmp).inject(sut);
    }

    @Test
    public void should__When_() {
        // Given

        // When

        // Then

    }
}
