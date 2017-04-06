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

		Fact fact = mock(Fact.class);
		when(fact.getFactDate())
				.thenReturn(date);

		when(mDtProv.toWeekDayString(date))
				.thenReturn(expected);

		setupFactReader(fact);

		// When
		List<ItemPresenter> presenters = sut.loadFactsfeed();

		// Then
		FirstWeekItemPresenter itemPresenter = (FirstWeekItemPresenter) presenters.get(0);
		assertThat(itemPresenter.getTitle())
				.isEqualTo(expected);
	}

	@Test
	public void should_wrap_fact_to_FactItemPresenter_When_loadFactsfeed() {
		// Given
		Fact expected = mock(Fact.class);
		setupFactReader(expected);

		// When
		List<ItemPresenter> presenters = sut.loadFactsfeed();

		// Then
		FactItemPresenter itemPresenter = (FactItemPresenter) presenters.get(1);
		Fact actualFact = itemPresenter.getFact();
		assertThat(actualFact).isEqualTo(expected);
	}

	void setupFactReader(Fact fact){
		when(mFactReader.loadFactsfeed())
				.thenReturn(new ArrayList<Fact>() {{
					add(fact);
				}});
	}
}
