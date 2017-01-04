package com.revents.chronolog.features;

import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FactTypeIntentExtractorTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    FactTypeIntentExtractor sut;

    @Mock
    FactReader mFactReader;

    @Test
    public void should_load_FactType_by_id_from_intent_When_extract() {
        // Given
        Intent data = mock(Intent.class);
        FactType expected = mock(FactType.class);
        long ftId = 42L;
        when(mFactReader.loadFactType(ftId))
                .thenReturn(expected);

        when(data.getLongExtra(FactTypeIntentExtractor.FACT_TYPE_ID_EXTRA_NAME,-1))
                .thenReturn(ftId);

        // When
        FactType actual = sut.extract(data);

        // Then
        assertEquals(expected, actual);
    }
}
