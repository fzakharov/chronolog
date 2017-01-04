package com.revents.chronolog.features;

import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactTypeGroup;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FactTypeGroupIntentExtractorTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    FactTypeGroupIntentExtractor sut;

    @Mock
    FactReader mFactReader;

    @Test
    public void should_load_FactTypeGroup_by_id_from_intent_When_extract() {
        // Given
        Intent data = mock(Intent.class);
        FactTypeGroup expected = mock(FactTypeGroup.class);
        long id = 42L;
        when(mFactReader.loadFactTypeGroup(id))
                .thenReturn(expected);

        when(data.getLongExtra(FactTypeGroupIntentExtractor.FACT_TYPE_GROUP_ID_EXTRA_NAME, -1))
                .thenReturn(id);

        // When
        FactTypeGroup actual = sut.extract(data);

        // Then
        assertEquals(expected, actual);
    }
}
