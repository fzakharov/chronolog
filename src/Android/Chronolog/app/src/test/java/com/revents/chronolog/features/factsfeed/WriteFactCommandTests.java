package com.revents.chronolog.features.factsfeed;

import com.revents.chronolog.app.FactBuilder;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.Fact;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WriteFactCommandTests {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    WriteFactCommand sut;

    @Mock
    FactWriter factWriter;

    @Mock
    FactBuilder factBuilder;

    @Test
    public void should_write_built_Fact_to_FactWriter_When_execute() {
        // Given
        Fact expected = mock(Fact.class);
        when(factBuilder.build())
                .thenReturn(expected);

        // When
        sut.execute();

        // Then
        verify(factWriter).write(expected);
    }
}
