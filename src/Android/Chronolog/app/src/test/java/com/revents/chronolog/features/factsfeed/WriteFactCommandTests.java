package com.revents.chronolog.features.factsfeed;

import com.revents.chronolog.app.FactBuilder;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.Fact;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WriteFactCommandTests {

    private WriteFactCommand sut;
    private FactWriter factWriter;
    private FactBuilder factBuilder;

    @Before
    public void setUp() {
        factWriter = mock(FactWriter.class);
        factBuilder = mock(FactBuilder.class);
        sut = new WriteFactCommand(factWriter, factBuilder);
    }

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
