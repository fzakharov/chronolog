package com.revents.chronolog;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.FastCursor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboCursor;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class GreenDaoFactWriterTests {
    private GreenDaoFactWriter sut;
    DateTimeProvider dtProv;
    private DaoSession daoSession;
    private Fact testFact;

    @Before
    public void setUp() throws Exception {

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(RuntimeEnvironment.application, null);
        Database db = openHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        testFact = new Fact(null, null, new Date(), 42, "str val");

        dtProv = mock(DateTimeProvider.class);
        sut = new GreenDaoFactWriter(dtProv, daoSession);
    }

    @Test
    public void Should_write_properties_When_write() {
        // Given
        Date expectedTimestamp = new Date();
        Mockito.when(dtProv.getDate())
                .thenReturn(expectedTimestamp);

        // When
        Fact actual = sut.write(testFact);

        // Then
        Fact saved = daoSession.getFactDao().readEntity(new RoboCursor(), 0);
    }

    @Test
    public void Should_set_id_When_write() {
        // Given
        Date expectedTimestamp = new Date();
        Mockito.when(dtProv.getDate())
                .thenReturn(expectedTimestamp);

        // When
        Fact actual = sut.write(testFact);

        // Then
        assertNotNull(actual.getId());
    }

    @Test
    public void Should_pass_data_with_current_timestamp_When_write_fact() {
        // Given
        Date expectedTimestamp = new Date();
        Mockito.when(dtProv.getDate())
                .thenReturn(expectedTimestamp);

        // When
        Fact actual = sut.write(testFact);

        // Then
        assertTrue(expectedTimestamp.getTime() - actual.getTimestamp().getTime() < 1000);
    }
}