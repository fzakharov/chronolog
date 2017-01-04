package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.db.FactReader;
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

public class SelectFactTypeActivityCommandTests {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    SelectFactTypeUiCommand sut;

    @Mock
    IntentFactory mIntentFactory;

    @Mock
    FactReader mFactReader;

    @Test
    public void should_start_FactTypesActivity_When_execute() {
        // Given
        int expectedReqCode = SelectFactTypeUiCommand.FACT_TYPE_ID_REQUEST_CODE;
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
    public void should_return_null_When_onResult_for_another_requestCode() {
        // Given
        Activity currentActivity = mock(Activity.class);
        int unknownRequestCode = 42;

        // When
        FactType actual = sut.onResult(currentActivity, unknownRequestCode, 24, new Intent());

        // Then
        assertNull(actual);
    }

    @Test
    public void should_return_FactType_by_id_from_intent_When_onResult() {
        // Given
        Activity currentActivity = mock(Activity.class);
        FactType expected = mock(FactType.class);
        long factTypeId = 42;

        Intent data = mock(Intent.class);
        when(data.getLongExtra(SelectFactTypeUiCommand.FACT_TYPE_ID_EXTRA_NAME, -1))
                .thenReturn(factTypeId);

        when(mFactReader.loadFactType(factTypeId))
                .thenReturn(expected);

        // When
        FactType actual = sut.onResult(currentActivity, SelectFactTypeUiCommand.FACT_TYPE_ID_REQUEST_CODE, RESULT_OK, data);

        // Then
        assertEquals(expected, actual);
    }
}
