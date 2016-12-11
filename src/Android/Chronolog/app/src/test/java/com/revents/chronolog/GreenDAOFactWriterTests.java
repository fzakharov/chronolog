package com.revents.chronolog;

import org.greenrobot.greendao.database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class GreenDaoFactWriterTests {
    DateTimeProvider mDateProvider;
    Database mDb;
    DaoSession mDaoSession;
    Date mCurentDate;

    FactType mTestFactType;
    Fact mTestFact;

    GreenDaoFactWriter sut;

    @Before
    public void setUp() throws Exception {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(RuntimeEnvironment.application, null);
        mDb = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(mDb).newSession();

        FactTypeDao ftd = mDaoSession.getFactTypeDao();
        mTestFactType = new FactType(null, "Fact", "Fact descr");
        ftd.insert(mTestFactType);
        mTestFact = new Fact(null, null, new Date(1), 55, "str val", ftd.insert(new FactType(null, "Fact", "Fact descr")));

        mDateProvider = mock(DateTimeProvider.class);
        sut = new GreenDaoFactWriter(mDateProvider, mDaoSession);

        mCurentDate = new Date(2);
        setupDateTimeProvider(mCurentDate);
    }

    @After
    public void after(){
        mDaoSession.clear();
        mDb.close();
    }

    @Test
    public void should_add_fact_with_current_timestamp_When_write_with_id_null() {
        // Given
        // When
        sut.write(mTestFact);

        // Then
        dbShouldContainFact(mTestFact, mCurentDate);
    }

    @Test
    public void should_update_properties_and_timestamp_When_update() {
        // Given
        sut.write(mTestFact);

        mTestFact.setStrValue("new str value");
        mTestFact.setIntValue(mTestFact.getIntValue() + 1);
        mTestFact.setFactDate(new Date(3));
        mTestFact.setFactType(mTestFactType);

        Date newTimestamp = new Date(4);
        setupDateTimeProvider(newTimestamp);

        // When
        sut.write(mTestFact);

        // Then
        dbShouldContainFact(mTestFact, newTimestamp);
    }

    @Test
    public void should_delete_fact_When_delete() {
        // Given
        sut.write(mTestFact);

        // When
        sut.delete(mTestFact);

        // Then
        Fact deleted = mDaoSession.getFactDao().load(mTestFact.getId());
        assertNull(deleted);
    }

    void setupDateTimeProvider(Date curentDate) {
        Mockito.when(mDateProvider.getDate())
                .thenReturn(curentDate);
    }

    void dbShouldContainFact(Fact expected, Date withTimestamp) {
        datesEquals(withTimestamp, expected.getTimestamp());
        Fact actual = mDaoSession.getFactDao().load(expected.getId());
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
        datesEquals(expected, actual, 0);
    }

    void datesEquals(Date expected, Date actual, long precission) {
        assertTrue(expected.getTime() - actual.getTime() <= precission);
    }
}