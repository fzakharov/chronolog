package com.revents.chronolog.features.feed;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class EditFactActivityExtractorTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    EditFactActivityExtractor sut;

    @Test
    public void should__When_() {
        // Given

        // When
        sut.extract(new EditFactActivity());

        // Then
    }
}
