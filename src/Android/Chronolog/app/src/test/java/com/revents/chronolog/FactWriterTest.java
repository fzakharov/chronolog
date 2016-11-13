package com.revents.chronolog;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;
import java.util.Date;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, packageName = "com.revents.chronolog")
public class FactWriterTest {
    private FactWriter sut;

    @Before
    public void setUp() throws Exception {
        sut = new FactWriter();
    }

    @Test
    public void Should__When_()
    {
        // Given
        Date expectedDate = new Date();
        int expectedIntValue = 42;
        String expectedStrValue = "some str value";

        Fact f = new Fact(expectedDate, expectedIntValue, expectedStrValue);

        // When
        sut.Write(f);

        // Then

    }

    // http://jameskbride.com/2016/02/13/android-tdd-series-test-driving-data-part-1-sqliteopenhelper.html

//    @Before
//    public void setUp() throws Exception {
//        String filePath = getClass().getResource("/sample.db").toURI().getPath();
//
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(
//                (new File(filePath)).getAbsolutePath(),
//                null,
//                SQLiteDatabase.OPEN_READWRITE);
//
//        // perform any db operations you want here
//    }
}