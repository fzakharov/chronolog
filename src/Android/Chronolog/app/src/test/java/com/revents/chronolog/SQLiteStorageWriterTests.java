package com.revents.chronolog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

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

    @Test
    public void Should__When_()
    {
        // Given
        Date stamp = new Date();
        Date factDate = new Date();
        int intVal=42;
        String strVal="str val";

        // WHen
        sut.writeRecord(stamp, factDate, intVal, strVal);

        // Then

    }
}
