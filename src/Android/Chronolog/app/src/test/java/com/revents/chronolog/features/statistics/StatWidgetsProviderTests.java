package com.revents.chronolog.features.statistics;

import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;

public class StatWidgetsProviderTests{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    StatWidgetsProvider sut;

    @Test
    public void should__When_() {
        // Given

        // When
        sut.getWidgetsList(mock(FactType.class));

        // Then
    }
}
