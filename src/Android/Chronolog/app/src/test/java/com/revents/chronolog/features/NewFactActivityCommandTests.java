package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.app.ParametrizedActivityCommand;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewFactActivityCommandTests {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    NewFactUiCommand sut;

    @Mock
    UiCommand<FactType> mSelectFactTypeCommand;

    @Mock
    ParametrizedActivityCommand<Fact, FactType> mEditFactCommand;

    @Test
    public void should_start_FactType_selection_When_execute() {
        // Given
        Activity currentActivity = mock(Activity.class);

        // When
        sut.execute(currentActivity);

        // Then
        verify(mSelectFactTypeCommand).execute(currentActivity);
    }

    @Test
    public void should_execute_edit_fact_and_return_null_When_onResult() {
        // Given
        int reqCode = 1;
        int resCode = 2;
        Intent data = mock(Intent.class);
        Activity currentActivity = mock(Activity.class);
        FactType factType = mock(FactType.class);

        when(mSelectFactTypeCommand.onResult(currentActivity, reqCode, resCode, data))
                .thenReturn(factType);

        // When
        Fact actual = sut.onResult(currentActivity, reqCode, resCode, data);

        // Then
        assertNull(actual);
        verify(mEditFactCommand).execute(currentActivity, factType);
    }

    @Test
    public void should_return_fact_from_edit_command_When_onResult() {
        // Given
        int reqCode = 1;
        int resCode = 2;
        Intent data = mock(Intent.class);
        Fact expected = mock(Fact.class);
        Activity currentActivity = mock(Activity.class);

        when(mEditFactCommand.onResult(currentActivity, reqCode, resCode, data))
                .thenReturn(expected);

        // When
        Fact actual = sut.onResult(currentActivity, reqCode, resCode, data);

        // Then
        assertEquals(expected, actual);
    }

}
