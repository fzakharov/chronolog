package com.revents.chronolog.app;

import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

@RunWith(RobolectricGradleTestRunner.class)
public class AppModuleTests {

    private AppModule sut;

    @Before
    public void setUp() {

        sut = new AppModule(null);
    }

    @Test
    public void should__When_() {
        // Given

        // When
        GreenDaoFactWriter fw = (GreenDaoFactWriter)sut.provideFactWriter();

        // Then

    }
}
