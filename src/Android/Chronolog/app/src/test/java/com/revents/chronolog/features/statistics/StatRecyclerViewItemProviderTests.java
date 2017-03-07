package com.revents.chronolog.features.statistics;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;

public class StatRecyclerViewItemProviderTests{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    StatRecyclerViewItemProvider sut;

    @Test
    public void should__When_() {
        // Given
        Widget widget = mock(Widget.class);

        // When
        sut.getResourceId(widget);

        // Then
    }
}
