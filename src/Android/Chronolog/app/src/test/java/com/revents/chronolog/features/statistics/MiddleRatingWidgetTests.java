package com.revents.chronolog.features.statistics;


import com.revents.chronolog.app.*;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MiddleRatingWidgetTests {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	DataContext mDataContext;

	@Mock
	DateTimeProvider mDtProv;

	@Mock
	FactType mFactType;

	@InjectMocks
	MiddleRatingWidget sut;
	private ArrayList<Fact> mFacts;

	@Before
	public void setUp() {
		mFacts = new ArrayList<>();

		when(mDataContext.getFactsByType(mFactType))
				.thenReturn(mFacts);
	}


	@Test
	public void should_return_0_for_empty_fact_list_When_getMiddleRating() {
		// Given
		float expected = 0f;

		// When
		float actual = sut.getMiddleRating();

		// Then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void should_return_middle_for_fact_list_When_getMiddleRating() {
		// Given
		long fact1 = 3;
		long fact2 = 4;
		float expected = 3.5f;

		addFactWithLongValue(fact1);
		addFactWithLongValue(fact2);

		// When
		float actual = sut.getMiddleRating();

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
