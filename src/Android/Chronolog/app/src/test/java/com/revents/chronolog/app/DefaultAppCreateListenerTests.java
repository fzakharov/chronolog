package com.revents.chronolog.app;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class DefaultAppCreateListenerTests {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    DefaultAppCreateListener sut;

    @Mock
    FactReader mFactReader;

    @Mock
    FactWriter mFactWriter;

    // TODO: 21.01.2017 refactor: move to database update
    @Test
    public void should_create_coffee_FactType_When_onCreate() {
        // Given
        ArgumentCaptor<FactTypeGroup> ftgCaptor = ArgumentCaptor.forClass(FactTypeGroup.class);
        ArgumentCaptor<ValueDescriptor> vdCaptor = ArgumentCaptor.forClass(ValueDescriptor.class);
        ArgumentCaptor<FactType> ftCaptor = ArgumentCaptor.forClass(FactType.class);

        // When
        sut.onCreate();

        // Then
        verify(mFactWriter).write(ftgCaptor.capture());
        assertEquals(DefaultAppCreateListener.OTHER_GROUP_ID, ftgCaptor.getValue().getId());

        verify(mFactWriter).write(vdCaptor.capture());
        assertEquals(DefaultAppCreateListener.DEFAULT_VALUE_DESCR_ID, vdCaptor.getValue().getId());

        verify(mFactWriter).write(ftCaptor.capture());
        assertEquals(DefaultAppCreateListener.COFFEE_FACT_TYPE_ID, ftCaptor.getValue().getId());
    }
}
