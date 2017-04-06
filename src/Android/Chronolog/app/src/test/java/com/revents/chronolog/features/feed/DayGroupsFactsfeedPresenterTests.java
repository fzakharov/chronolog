package com.revents.chronolog.features.feed;


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

	@InjectMocks
	DayGroupsFactsfeedPresenter sut;

	@Test
	public void should_wrap_fact_to_FactItemPresenter_When_loadFactsfeed() {
		// Given
		Fact expected = mock(Fact.class);

		when(mFactReader.loadFactsfeed())
				.thenReturn(new ArrayList<Fact>() {{
					add(expected);
				}});

		// When
		List<ItemPresenter> presenters = sut.loadFactsfeed();

		// Then
		FactItemPresenter itemPresenter = (FactItemPresenter) presenters.get(0);
		Fact actualFact = itemPresenter.getFact();
		assertThat(actualFact).isEqualTo(expected);
	}
}
