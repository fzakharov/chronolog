package com.revents.chronolog;

import org.greenrobot.greendao.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class GreenDaoFactWriterTests {
    private GreenDaoFactWriter sut;
    DateTimeProvider dtProv;
    private DaoSession daoSession;

    @Before
    public void setUp() throws Exception {

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(RuntimeEnvironment.application, null);
        Database db = openHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        dtProv = mock(DateTimeProvider.class);
        sut = new GreenDaoFactWriter(dtProv);
    }

    @Test
    public void Should_pass_data_with_current_timestamp_When_write_fact()
    {
        // Given
        Date expectedTimestamp = new Date();
        Date expectedDate = new Date();
        int expectedIntValue = 42;
        String expectedStrValue = "some str value";

        Mockito.when(dtProv.getDate())
                .thenReturn(expectedTimestamp);

        Fact fact = new Fact(-1, null, expectedDate, expectedIntValue,expectedStrValue);

        // When
        Fact actual = sut.write(fact);

        // Then
        assertTrue(expectedTimestamp.getTime() - actual.getTimestamp().getTime() < 1000);
    }
}