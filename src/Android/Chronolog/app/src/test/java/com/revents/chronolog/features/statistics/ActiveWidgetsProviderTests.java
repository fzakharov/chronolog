package com.revents.chronolog.features.statistics;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActiveWidgetsProviderTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    WidgetFactory<Object> mWidgetFactory;

    @Mock
    WidgetsRegistry<Object> mWidgetsRegistry;

    @InjectMocks
    ActiveWidgetsProvider<Object> sut;

    @Test
    public void should_instantiate_widgets_by_FactType_When_getWidgetsList() {
        // Given
        Object data = new Object();
        Widget expectedWidgetOne = mock(Widget.class);
        String nameOne = "nameOne";

        Widget expectedWidgetTwo = mock(Widget.class);
        String nameTwo = "nameTwo";

        String[] names = new String[]{nameOne, nameTwo};

        when(mWidgetsRegistry.getActiveWidgets(data))
                .thenReturn(names);

        when(mWidgetFactory.createWidget(nameOne, data))
                .thenReturn(expectedWidgetOne);

        when(mWidgetFactory.createWidget(nameTwo, data))
                .thenReturn(expectedWidgetTwo);

        // When
        List<Widget> list = sut.getWidgetsList(data);

        // Then
        assertThat(list)
                .containsExactly(expectedWidgetOne, expectedWidgetTwo);
    }
}
