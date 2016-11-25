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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


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

        sut.write(testFact);

        // When
        Fact saved = daoSession.getFactDao().load(testFact.getId());

        // Then
        assertEquals(saved.getStrValue(), testFact.getStrValue());
        assertEquals(saved.getIntValue(), testFact.getIntValue());
        assertTrue(saved.getFactDate().getTime() - testFact.getFactDate().getTime() < 1000);
        assertTrue(saved.getFactDate().getTime() - expectedTimestamp.getTime() < 1000);
    }

}