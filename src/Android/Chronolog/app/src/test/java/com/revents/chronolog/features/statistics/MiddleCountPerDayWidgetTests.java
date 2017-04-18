package com.revents.chronolog.features.statistics;


import com.revents.chronolog.app.*;
import com.revents.chronolog.db.*;
import com.revents.chronolog.model.*;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO: 15.04.2017 copypaste with MiddleRatingWidgetTests
public class MiddleCountPerDayWidgetTests {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	FactReader mReader;

	@Mock
	DateTimeProvider mDtProv;

	@Mock
	FactType mFactType;

	@InjectMocks
	MiddleCountPerDayWidget sut;
	private ArrayList<Fact> mFacts;

	@Before
	public void setUp() {
		mFacts = new ArrayList<>();

		Date begin = new Date(1);
		Date end = new Date(100);

		when(mDtProv.getDate())
				.thenReturn(end);

		when(mDtProv.getEndDaysAgo(end, MiddleCountPerDayWidget.DaysAgo))
				.thenReturn(begin);

		when(mReader.loadFactsByType(mFactType, begin, end))
				.thenReturn(mFacts);
	}


	@Test
	public void should_return_0_for_empty_fact_list_When_getMiddleRating() {
		// Given
		float expected = 0f;

		// When
		float actual = sut.getMiddleCount();

		// Then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void should_return_middle_for_fact_list_When_getMiddleRating() {
		// Given
		long fact1 = 3;
		long fact2 = 4;
		float expected = (float) 4 / MiddleCountPerDayWidget.DaysAgo;

		addFactWithLongValue(fact1);
		addFactWithLongValue(fact2);

		addFactWithLongValue(fact1);
		addFactWithLongValue(fact2);

		// When
		float actual = sut.getMiddleCount();

		// Then
		assertThat(actual).isEqualTo(expected);
	}

	void addFactWithLongValue(long value) {
		Fact fact = mock(Fact.class);
		when(fact.getLongValue())
				.thenReturn(value);

		mFacts.add(fact);
	}
}
