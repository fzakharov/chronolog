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
        daoSession.clear();

        testFact = new Fact(null, null, new Date(), 42, 55, "str val");

        dtProv = mock(DateTimeProvider.class);
        sut = new GreenDaoFactWriter(dtProv, daoSession);
    }

    // TODO: 25.11.2016 clean testdb

    @Test
    public void should_add_fact_with_current_timestamp_When_write_with_id_null() {
        // Given
        Date curentDate = new Date();
        setupDateTimeProvider(curentDate);

        // When
        sut.write(testFact);
        daoSession.clear();

        // Then
        datesEquals(curentDate, testFact.getTimestamp());
        dbShouldContainFact(testFact);
    }

    @Test
    public void should_update_properties_and_timestamp_When_update() {
        // Given
        Date curentDate = new Date(1000);
        setupDateTimeProvider(curentDate);

        sut.write(testFact);
        daoSession.clear();

        testFact.setStrValue(testFact.getStrValue() + "addition");
        testFact.setIntValue(testFact.getIntValue() + 1);
        testFact.setFactDate(new Date(testFact.getFactDate().getTime() + 10000));
        testFact.setFactType(testFact.getFactType() + 1);

        curentDate = new Date(curentDate.getTime() + 10000);
        setupDateTimeProvider(curentDate);

        // When
        sut.write(testFact);
        daoSession.clear();

        // Then
        datesEquals(curentDate, testFact.getTimestamp());
        dbShouldContainFact(testFact);
    }

    void setupDateTimeProvider(Date curentDate) {
        Mockito.when(dtProv.getDate())
                .thenReturn(curentDate);
    }

    void dbShouldContainFact(Fact expected) {
        Fact actual = daoSession.getFactDao().load(expected.getId());
        factEquals(expected, actual);
    }

    void factEquals(Fact expected, Fact actual) {
        assertEquals(expected.getFactType(), actual.getFactType());
        assertEquals(expected.getStrValue(), actual.getStrValue());
        assertEquals(expected.getIntValue(), actual.getIntValue());
        datesEquals(expected.getFactDate(), actual.getFactDate());
        datesEquals(expected.getTimestamp(), actual.getTimestamp());
    }

    void datesEquals(Date expected, Date actual) {
        datesEquals(expected, actual, 1000);
    }

    void datesEquals(Date expected, Date actual, long precission) {
        assertTrue(expected.getTime() - actual.getTime() < precission);
    }
}