package com.revents.chronolog.features;

import android.app.Activity;

import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.FactEditor;
import com.revents.chronolog.features.NewFactActivityCommand;
import com.revents.chronolog.model.Fact;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewFactActivityCommandTests {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    NewFactActivityCommand sut;

    @Mock
    FactWriter factWriter;

    @Mock
    FactEditor factEditor;

    @Test
    public void should_start_FactType_selection_When_execute() {
        // Given
        Fact expected = mock(Fact.class);
        Activity currentActivity = mock(Activity.class);
        when(factEditor.newFact(currentActivity))
                .thenReturn(expected);

        // When
        sut.execute(currentActivity);

        // Then
        verify(factWriter).write(expected);
    }
}
