package com.revents.chronolog.features;

import android.app.Activity;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.db.FactWriter;
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
    ActivityCommand mSelectFactTypeCommand;

    @Test
    public void should_start_FactType_selection_When_execute() {
        // Given
        Activity currentActivity = mock(Activity.class);

        // When
        sut.execute(currentActivity);

        // Then
        verify(mSelectFactTypeCommand).execute(currentActivity);
    }
}
