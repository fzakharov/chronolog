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
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class ChronologAppRoboTests {

    private AppCreateListener mAppCreateListener = mock(AppCreateListener.class);
    private FakeChronologApp sut;

    @Before
    public void setUp() throws Exception {

        sut = (FakeChronologApp) RuntimeEnvironment.application;
    }

    @Test
    public void should_call_AppCreateListener_onCreate_When_onCreate() {
        // Given
        // When
        // Then
        AppCreateListener createListener = sut.getCreateListener();
        verify(createListener).onCreate();
    }
}
