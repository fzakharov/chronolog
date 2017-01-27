package com.revents.chronolog.app;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class JavaDateTimeProviderTests {

    @Test
    public void should__When_() {
        // Given
        String expected = "15 янв 2017";
        Calendar calendar = new GregorianCalendar(2017, 0, 15, 13, 24, 56);
        Date date = calendar.getTime();
        JavaDateTimeProvider sut = new JavaDateTimeProvider();

        // When
        String actual = sut.toDateString(date);

        // Then
        assertEquals(expected, actual);
    }
}
