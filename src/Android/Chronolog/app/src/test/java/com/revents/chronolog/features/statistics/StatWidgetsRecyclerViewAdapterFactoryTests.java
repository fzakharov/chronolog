package com.revents.chronolog.features.statistics;

import android.support.v7.widget.RecyclerView;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;

public class StatWidgetsRecyclerViewAdapterFactoryTests{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    StatWidgetsRecyclerViewAdapterFactory sut;

    @Mock
    FactReader mFactReader;

    @Test
    public void should__When_() {
        // Given
        FactType factType = mock(FactType.class);

        // When
        RecyclerView.Adapter actual = sut.create(factType);

        // Then

    }
}
