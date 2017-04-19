package com.revents.chronolog.app;


import com.revents.chronolog.db.*;
import com.revents.chronolog.model.*;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChronologDataContextTests {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	FactReader mReader;

	@Mock
	DateTimeProvider mDtProv;

	@Mock
	FactType mFactType;

	@InjectMocks
	ChronologDataContext sut;

	private ArrayList<Fact> mFacts = new ArrayList<>();

	@Test
	public void should_return_default_period_When_getPeriodDays() {
		// Given
		// When
		int actual = sut.getPeriodDays();

		// Then
		assertThat(actual).isEqualTo(ChronologDataContext.DEFAULT_PERIOD_DAYS);
	}

	@Test
	public void should_return_new_period_When_getPeriodDays() {
		// Given
		int expected = 42;
		sut.setPeriodDays(expected);

		// When
		int actual = sut.getPeriodDays();

		// Then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void should_return_facts_from_FactsReader_for_current_period_When_getFactsByType() {
		// Given
		mFacts.add(mock(Fact.class));

		int periodDays = 42;
		sut.setPeriodDays(periodDays);

		Date end = new Date();
		Date begin = new Date(100000);

		when(mDtProv.getDate())
				.thenReturn(end);

		when(mDtProv.getEndDaysAgo(end, periodDays))
				.thenReturn(begin);

		when(mReader.loadFactsByType(mFactType, begin, end))
				.thenReturn(mFacts);

		// When
		List<Fact> actual = sut.getFactsByType(mFactType);

		// Then
		assertThat(actual).isEqualTo(mFacts);
	}
}
