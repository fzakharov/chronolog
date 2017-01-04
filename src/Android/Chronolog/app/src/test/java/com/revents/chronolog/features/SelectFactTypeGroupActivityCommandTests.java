package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactTypeGroup;

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

public class SelectFactTypeGroupActivityCommandTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    SelectFactTypeGroupUiCommand sut;

    @Mock
    IntentFactory mIntentFactory;

    @Mock
    IntentExtractor<FactTypeGroup> mExtractor;

    @Test
    public void should_startActivityForResult_When_execute() {
        // Given
        Activity currentActivity = mock(Activity.class);
        Intent expectedIntent = mock(Intent.class);

        when(mIntentFactory.Create(currentActivity, FactTypeGroupsActivity.class))
                .thenReturn(expectedIntent);

        // When
        sut.execute(currentActivity);

        // Then
        verify(currentActivity)
                .startActivityForResult(expectedIntent,
                        SelectFactTypeGroupUiCommand.FACT_TYPE_GROUP_ID_REQUEST_CODE);
    }

    @Test
    public void should_return_null_When_onResult_for_another_requestCode() {
        // Given
        int unknownRequestCode = 42;

        // When
        FactTypeGroup actual = sut.onResult(mock(Activity.class), unknownRequestCode, 24, new Intent());

        // Then
        assertNull(actual);
    }

    @Test
    public void should_return_FactTypeGroup_from_intent_When_onResult() {
        // Given
        FactTypeGroup expected = mock(FactTypeGroup.class);
        Intent data = mock(Intent.class);
        when(mExtractor.extract(data))
                .thenReturn(expected);

        // When
        FactTypeGroup actual = sut.onResult(mock(Activity.class),
                SelectFactTypeGroupUiCommand.FACT_TYPE_GROUP_ID_REQUEST_CODE, RESULT_OK, data);

        // Then
        assertEquals(expected, actual);
    }
}
