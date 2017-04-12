package com.revents.chronolog.features.feed;


import com.revents.chronolog.app.*;
import com.revents.chronolog.db.*;
import com.revents.chronolog.model.*;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DayGroupsFactsfeedPresenterTests {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	FactReader mFactReader;

	@Mock
	DateTimeProvider mDtProv;

	@InjectMocks
	DayGroupsFactsfeedPresenter sut;

	@Test
	public void should_add_DateItemPresenter_before_first_fact_When_loadFactsfeed() {
		// Given
		Date date = new Date();
		String expected = "Today";

		when(mDtProv.getDatePart(date))
				.thenReturn(date);

		Fact fact = mock(Fact.class);
		when(fact.getFactDate())
				.thenReturn(date);

		setupFactReader(fact);
		setUpDtProvToWeekDay(date, expected);

		// When
		FirstWeekItemPresenter itemPresenter = getLoadedFact(0);

		// Then
		assertThat(itemPresenter.getTitle())
				.isEqualTo(expected);
	}

	@Test
	public void should_wrap_fact_to_FactItemPresenter_When_loadFactsfeed() {
		// Given
		Fact expected = mock(Fact.class);
		setupFactReader(expected);
		setUpDtProvToWeekDay(expected.getFactDate(), "wed");

		// When
		FactItemPresenter itemPresenter = getLoadedFact(1);

		// Then
		assertThat(itemPresenter.getFact())
				.isEqualTo(expected);
	}

	<T> T getLoadedFact(int index){
		return (T) sut.loadFactsfeed().get(index);
	}

	void setUpDtProvToWeekDay(Date date, String week){
		when(mDtProv.toFullDateStringWithWeekDay(date))
				.thenReturn(week);
	}

	void setupFactReader(Fact fact){
		when(mFactReader.loadFactsfeed())
				.thenReturn(new ArrayList<Fact>() {{
					add(fact);
				}});
	}
}
