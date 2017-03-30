package com.revents.chronolog.features.statistics;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.statistics.FactTypeStatisticsActivity;
import com.revents.chronolog.features.type.FactTypeIntentExtractor;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShowStatUiActionTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ShowStatUiAction sut;

    @Mock
    IntentFactory mIntentFactory;

    @Test
    public void should_start_FactTypeStatActivity_When_execute() {
        // Given
        Activity currentActivity = mock(Activity.class);
        Intent expectedIntent = mock(Intent.class);

        long expectedFactTypeId = 42L;

        Fact fact = mock(Fact.class);
        when(fact.getFactTypeId())
                .thenReturn(expectedFactTypeId);


        when(mIntentFactory.Create(currentActivity, FactTypeStatisticsActivity.class))
                .thenReturn(expectedIntent);

        // When
        sut.execute(currentActivity, fact);

        // Then
        verify(currentActivity)
                .startActivity(expectedIntent);

        verify(expectedIntent)
                .putExtra(FactTypeIntentExtractor.FACT_TYPE_ID_EXTRA_NAME, expectedFactTypeId);
    }
}
