package com.revents.chronolog.features;

import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.ValueDescriptor;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValueDescriptorIntentExtractorTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ValueDescriptorIntentExtractor sut;

    @Mock
    FactReader mFactReader;

    @Test
    public void should_load_ValueDescriptor_by_id_from_intent_When_extract() {
        // Given
        Intent data = mock(Intent.class);
        ValueDescriptor expected = mock(ValueDescriptor.class);
        long id = 42L;
        when(mFactReader.loadValueDescriptor(id))
                .thenReturn(expected);

        when(data.getLongExtra(ValueDescriptorIntentExtractor.VALUE_DESCRIPTOR_ID_EXTRA_NAME, -1))
                .thenReturn(id);

        // When
        ValueDescriptor actual = sut.extract(data);

        // Then
        assertEquals(expected, actual);
    }
}
