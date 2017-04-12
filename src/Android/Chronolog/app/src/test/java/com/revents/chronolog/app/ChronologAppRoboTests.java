package com.revents.chronolog.app;

import com.revents.chronolog.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
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
