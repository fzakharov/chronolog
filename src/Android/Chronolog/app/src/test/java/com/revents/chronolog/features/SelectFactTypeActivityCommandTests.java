package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SelectFactTypeActivityCommandTests {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    SelectFactTypeActivityCommand sut;

    @Mock
    IntentFactory mIntentFactory;

    @Test
    public void should_start_FactTypesActivity_When_execute() {
        // Given
        int expectedReqCode = SelectFactTypeActivityCommand.FACT_TYPE_ID_REQUEST_CODE;
        Class<FactTypesActivity> factTypesActivityClass = FactTypesActivity.class;
        Activity currentActivity = mock(Activity.class);
        Intent expectedIntent = mock(Intent.class);

        when(mIntentFactory.Create(currentActivity, factTypesActivityClass))
                .thenReturn(expectedIntent);

        // When
        sut.execute(currentActivity);

        // Then
        verify(currentActivity)
                .startActivityForResult(expectedIntent, expectedReqCode);
    }

    @Test
    public void should_do_nothing_When_onResult() {
        // Given
        // When
        sut.onResult(42, 24, new Intent());

        // Then
    }
}
