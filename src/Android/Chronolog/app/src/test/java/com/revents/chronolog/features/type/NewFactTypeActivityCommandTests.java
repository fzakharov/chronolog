package com.revents.chronolog.features.type;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static android.app.Activity.RESULT_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewFactTypeActivityCommandTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    NewFactTypeResultUiCommand sut;

    @Mock
    IntentFactory mIntentFactory;

    @Mock
    FactReader mFactReader;

    @Test
    public void should_start_EditFactTypeActivity_When_execute() {
        // Given
        Activity currentActivity = mock(Activity.class);
        Intent data = mock(Intent.class);

        when(mIntentFactory.Create(currentActivity, EditFactTypeActivity.class))
                .thenReturn(data);

        // When
        sut.execute(currentActivity);

        // Then
        verify(currentActivity)
                .startActivityForResult(data, NewFactTypeResultUiCommand.NEW_FACT_TYPE_REQUEST_CODE);
    }

    @Test
    public void should_return_null_When_onResult_for_another_request_code() {
        // Given
        Activity currentActivity = mock(Activity.class);
        Intent data = mock(Intent.class);

        // When
        FactType actual = sut.onResult(currentActivity, 42, 33, data);

        // Then
        assertNull(actual);
    }

    @Test
    public void should_return_FactType_by_id_When_onResult() {
        // Given
        Activity currentActivity = mock(Activity.class);
        long factTypeId = 42l;

        Intent data = mock(Intent.class);
        when(data.getLongExtra(FactTypeIntentExtractor.FACT_TYPE_ID_EXTRA_NAME, -1))
                .thenReturn(factTypeId);

        FactType expected = new FactType();
        when(mFactReader.loadFactType(factTypeId))
                .thenReturn(expected);

        // When
        FactType actual = sut.onResult(
                currentActivity,
                NewFactTypeResultUiCommand.NEW_FACT_TYPE_REQUEST_CODE,
                RESULT_OK,
                data);

        // Then
        assertEquals(expected, actual);
    }
}
