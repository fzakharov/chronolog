package com.revents.chronolog.features.value;

import android.widget.ListView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.HeadedLvItem;
import com.revents.chronolog.model.ValueDescriptor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowListView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValueTypesActivityRoboTests extends ActivityRoboTestsBase<ValueTypesActivity> {

    ValueTypesProvider mValueTypesProvider;
    ValueType mTestValueType;
    FactWriter mFactWriter;

    @Before
    public void setUp() throws Exception {
        mValueTypesProvider = mock(ValueTypesProvider.class);
        mFactWriter = mock(FactWriter.class);

        mTestValueType = new ValueType("className", "Value name", "value description");
        when(mValueTypesProvider.getValueTypes())
                .thenReturn(new ValueType[]{mTestValueType});

        sutBuilder = Robolectric.buildActivity(ValueTypesActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
    }

    @Test
    public void should_write_new_ValueDescriptor_When_click() {
        // Given
        ListView lv = (ListView) viewById(R.id.valueTypesLv);

        ArgumentCaptor<ValueDescriptor> argument = ArgumentCaptor.forClass(ValueDescriptor.class);

        // When
        Shadows.shadowOf(lv).performItemClick(0);

        // Then
        verify(mFactWriter).write(argument.capture());
        ValueDescriptor actual = argument.getValue();
        assertEquals(mTestValueType.className, actual.getClassName());
        assertEquals(mTestValueType.name, actual.getName());
        assertEquals(mTestValueType.description, actual.getDescription());
    }

    @Test
    public void should_fill_list_from_provider_When_resume() {
        // Given
        ListView lv = (ListView) viewById(R.id.valueTypesLv);

        // When
        HeadedLvItem item = (HeadedLvItem) lv.getAdapter().getItem(0);

        // Then
        assertEquals(mTestValueType.className, item.id);
        assertEquals(mTestValueType.name, item.header);
        assertEquals(mTestValueType.description, item.content);
    }

    private void inject(final ValueTypesActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(mValueTypesProvider, mFactWriter);
                return null;
            }
        }).when(cmp).inject(sut);
    }
}
