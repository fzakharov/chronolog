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

public class SelectFactTypeGroupActivityCommandTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    SelectFactTypeGroupActivityCommand sut;

    @Mock
    IntentFactory mIntentFactory;

    @Test
    public void should_startActivityForResult_When_execute() {
        // Given
        Activity currentActivity = mock(Activity.class);
        Intent data = mock(Intent.class);

        when(mIntentFactory.Create(currentActivity, FactTypeGroupsActivity.class))
                .thenReturn(data);

        // When
        sut.execute(currentActivity);

        // Then
        verify(currentActivity)
                .startActivityForResult(data, FactTypeGroupsActivity.FACT_TYPE_GROUP_ID_REQUEST_CODE);
    }
}
