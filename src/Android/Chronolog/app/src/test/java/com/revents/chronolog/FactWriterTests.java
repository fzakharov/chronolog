package com.revents.chronolog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = LOLLIPOP, packageName = "com.revents.chronolog")
@RunWith(MockitoJUnitRunner.class)
public class FactWriterTests {
    private FactWriter sut;
    private DbWriter writer;
    DateTimeProvider dtProv;

    @Before
    public void setUp() throws Exception {

        dtProv = mock(DateTimeProvider.class);
        writer = mock(DbWriter.class);
        sut = new FactWriter(writer, dtProv);
    }

    @Test
    public void Should__When_()
    {
        // Given
        Date expectedTimestamp = new Date();
        Date expectedDate = new Date();
        int expectedIntValue = 42;
        String expectedStrValue = "some str value";

        Mockito.when(dtProv.getDate()).thenReturn(expectedTimestamp);
        Fact f = new Fact(expectedDate, expectedIntValue, expectedStrValue);

        // When
        sut.write(f);

        // Then
        verify(writer, atLeastOnce()).writeRecord(expectedTimestamp, expectedDate, expectedIntValue, expectedStrValue);
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