package com.revents.chronolog.features.statistics;

import com.revents.chronolog.R;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class StatRecyclerViewItemProviderTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    StatRecyclerViewItemProvider sut;

    @Test
    public void should_return_resourceId_When_getResourceId() {
        // Given
        int expected = R.layout.middle_rating_widget_rv_item;
        Widget widget = new MiddleRatingWidget(mock(FactType.class));

        // When
        int actual = sut.getResourceId(widget);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
