package com.revents.chronolog.features.feed;


import com.revents.chronolog.app.*;

import org.junit.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FirstWeekItemPresenterTests {

	DateTimeProvider mDtProv;
	Date mDate;
	FirstWeekItemPresenter sut;

	@Before
	public void setUp(){
		mDtProv = mock(DateTimeProvider.class);
		mDate = new Date(142000);
		sut = new FirstWeekItemPresenter(mDate, mDtProv);
	}

	@Test
	public void should_return_full_date_When_getTitle_for_not_today_date() {
		// Given
		String expected = "четверг 21.01.2001";

		Date today = new Date(420000);
		when(mDtProv.getDate())
				.thenReturn(today);

		when(mDtProv.getDatePart(mDate))
				.thenReturn(mDate);

		when(mDtProv.getDatePart(today))
				.thenReturn(today);

		when(mDtProv.toFullDateStringWithWeekDay(mDate))
				.thenReturn(expected);

		// When
		String actual = sut.getTitle();

		// Then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void should_return_today_When_getTitle_for_today_date() {
		// Given
		String expected = FirstWeekItemPresenter.TODAY_STRING;
		when(mDtProv.getDate())
				.thenReturn(mDate);

		when(mDtProv.getDatePart(mDate))
				.thenReturn(new Date(mDate.getTime() - 10000));

		// When
		String actual = sut.getTitle();

		// Then
		assertThat(actual).isEqualTo(expected);
	}
}
