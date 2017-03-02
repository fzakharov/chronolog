package com.revents.chronolog.features.statistics;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class StatRecyclerViewItemProviderTests{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    StatRecyclerViewItemProvider sut;

    @Test
    public void should__When_() {
        // Given

        // When
        sut.getResourceId(null);

        // Then
    }
}
