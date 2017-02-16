package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.statistics.FactTypeStatisticsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShowStatUiCommandTests{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ShowStatUiCommand sut;

    @Mock
    IntentFactory mIntentFactory;

    @Test
    public void should_start_FactTypeStatActivity_When_execute() {
        // Given
        Activity currentActivity = mock(Activity.class);
        Intent expectedIntent = mock(Intent.class);

        when(mIntentFactory.Create(currentActivity, FactTypeStatisticsActivity.class))
                .thenReturn(expectedIntent);

        // When
        sut.execute(currentActivity);

        // Then
        verify(currentActivity)
                .startActivity(expectedIntent);
    }
}
