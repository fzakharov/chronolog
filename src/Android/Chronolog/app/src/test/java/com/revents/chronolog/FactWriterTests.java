package com.revents.chronolog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FactWriterTests {
    private FactWriter sut;
    private StorageWriter writer;
    DateTimeProvider dtProv;

    @Before
    public void setUp() throws Exception {

        dtProv = mock(DateTimeProvider.class);
        writer = mock(StorageWriter.class);
        sut = new FactWriter(writer, dtProv);
    }

    @Test
    public void Should_pass_data_with_current_timestamp_When_write_fact()
    {
        // Given
        Date expectedTimestamp = new Date();
        Date expectedDate = new Date();
        int expectedIntValue = 42;
        String expectedStrValue = "some str value";

        Mockito.when(dtProv.getDate())
                .thenReturn(expectedTimestamp);

        Fact fact = new Fact(expectedDate, expectedIntValue, expectedStrValue);

        // When
        sut.write(fact);

        // Then
        verify(writer, atLeastOnce()).writeRecord(expectedTimestamp, expectedDate, expectedIntValue, expectedStrValue);
    }
}