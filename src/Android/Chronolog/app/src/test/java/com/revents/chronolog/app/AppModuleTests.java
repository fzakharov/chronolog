package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.features.NewFactActivityCommand;
import com.revents.chronolog.model.DaoSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = TestChronologApp.class)
public class AppModuleTests {

    private AppModule sut;

    @Before
    public void setUp() {

        sut = new AppModule(RuntimeEnvironment.application);
    }

    @Test
    public void should_return_DaoSession_When_provideDaoSession() {
        // Given // When
        DaoSession session = sut.provideDaoSession();

        // Then
        assertNotNull(session);
    }

    @Test
    public void should_return_ctor_passed_context_When_provideContext() {
        // Given // When
        Context ctx = sut.provideContext();

        // Then
        assertEquals(ctx, RuntimeEnvironment.application);
    }

    @Test
    public void should_create_JavaDateTimeProvider_When_provideDateTimeProvider() {
        // Given // When
        JavaDateTimeProvider jdtp = (JavaDateTimeProvider) sut.provideDateTimeProvider();

        // Then
        assertNotNull(jdtp);
    }

    @Test
    public void should_create_GreenDaoFactWriter_When_provideFactWriter() {
        // Given // When
        GreenDaoFactWriter fw = (GreenDaoFactWriter) sut.provideFactWriter(mock(DateTimeProvider.class), mock(DaoSession.class));

        // Then
        assertNotNull(fw);
    }

    @Test
    public void should_create_NewFactActivityCommand_When_provideNewFactActivityCommand() {
        // Given // When
        NewFactActivityCommand cmd = (NewFactActivityCommand) sut.provideNewFactActivityCommand();

        // Then
        assertNotNull(cmd);
    }
}
