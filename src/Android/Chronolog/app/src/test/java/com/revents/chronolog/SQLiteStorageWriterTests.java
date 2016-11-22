package com.revents.chronolog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, packageName = "com.revents.chronolog")
public class SQLiteStorageWriterTests{

    SQLiteStorageWriter sut;

    @Before
    public void setUp() throws Exception {
        sut = new SQLiteStorageWriter();
    }

    @After
    public void tearDown() throws Exception {

    }

    // http://jameskbride.com/2016/02/13/android-tdd-series-test-driving-data-part-1-sqliteopenhelper.html
    // http://greenrobot.org/greendao/
    @Test
    public void Should_return_new_record_id_When_writeRecord()
    {
        // Given
        long expectedId = 42;

        Date stamp = new Date();
        Date factDate = new Date();
        int intVal=2;
        String strVal="str val";

        // When
        long actualId = sut.writeRecord(stamp, factDate, intVal, strVal);

        // Then
        assertEquals(actualId, expectedId);
    }
}
