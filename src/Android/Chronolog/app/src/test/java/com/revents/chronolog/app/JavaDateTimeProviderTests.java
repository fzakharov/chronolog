package com.revents.chronolog.app;

import org.junit.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class JavaDateTimeProviderTests {
	JavaDateTimeProvider sut;

	@Before
	public void setUo(){
		sut = new JavaDateTimeProvider();
	}

    @Test
    public void should_format_as_string_When_toDateString() {
        // Given
        String expected = "15-янв-2017";
        Calendar calendar = new GregorianCalendar(2017, 0, 15, 13, 24, 56);
        Date date = calendar.getTime();

        // When
        String actual = sut.toDateString(date);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_week_day_When_toWeekDayString() {
        // Given
        String expected = "воскресенье, 15 января 2017";
        Calendar calendar = new GregorianCalendar(2017, 0, 15, 13, 24, 56);
        Date date = calendar.getTime();

        // When
        String actual = sut.toFullDateStringWithWeekDay(date);

        // Then
        assertEquals(expected, actual);
    }
}
