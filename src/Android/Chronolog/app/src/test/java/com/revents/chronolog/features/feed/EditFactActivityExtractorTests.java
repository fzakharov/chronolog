package com.revents.chronolog.features.feed;

import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditFactActivityExtractorTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    EditFactActivityExtractor sut;

    @Test
    public void should_extract_Fact_fields_from_activity_When_extract() {
        // Given
        Long id = 33L;
        Date date = new Date();
        Long value = 42L;
        String descr = "description text";
        long factTypeId = 44L;

        EditFactActivity activity = mock(EditFactActivity.class);

        when(activity.getFactId())
                .thenReturn(id);

        when(activity.getFactDate())
                .thenReturn(date);

        when(activity.getFactValue())
                .thenReturn(value);

        when(activity.getFactDescription())
                .thenReturn(descr);

        FactType factType = new FactType();
        factType.setId(factTypeId);
        when(activity.getFactType())
                .thenReturn(factType);

        // When
        Fact actual = sut.extract(activity);

        // Then
        assertEquals(id, actual.getId());
        assertEquals(null, actual.getTimestamp());
        assertEquals(date, actual.getFactDate());
        assertEquals(value, actual.getLongValue());
        assertEquals(descr, actual.getStrValue());
        assertEquals(factTypeId, actual.getFactTypeId());
        assertEquals(factType, actual.getFactType());
    }
}
