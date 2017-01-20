package com.revents.chronolog.app;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.feed.EditFactActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class ChronologAppRoboTests {

    private AppCreateListener mAppCreateListener = mock(AppCreateListener.class);
    private FakeChronologApp sut = (FakeChronologApp) RuntimeEnvironment.application;

    @Before
    public void setUp() throws Exception {

        inject(sut);
    }

    private void inject(final FakeChronologApp app) {
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                app.inject(mAppCreateListener);
                return null;
            }
        }).when(cmp).inject(sut);
    }

    @Test
    public void should_call_AppCreateListener_onCreate_When_onCreate() {
        // Given
        // When
        sut.onCreate();

        // Then
    }
}
