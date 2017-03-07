package com.revents.chronolog.features.statistics;

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

public class StatWidgetsProviderTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    WidgetFactory mWidgetFactory;

    @Mock
    WidgetsRegistry mWidgetsRegistry;

    @InjectMocks
    StatWidgetsProvider sut;

    @Test
    public void should_instantiate_widgets_by_FactType_When_getWidgetsList() {
        // Given
        FactType factType = mock(FactType.class);
        Widget expectedWidgetOne = mock(Widget.class);
        String nameOne = "nameOne";

        Widget expectedWidgetTwo = mock(Widget.class);
        String nameTwo = "nameTwo";

        String[] names = new String[]{nameOne, nameTwo};

        when(mWidgetsRegistry.getActiveWidgets(factType))
                .thenReturn(names);

        when(mWidgetFactory.createWidget(nameOne, factType))
                .thenReturn(expectedWidgetOne);

        when(mWidgetFactory.createWidget(nameTwo, factType))
                .thenReturn(expectedWidgetTwo);

        // When
        List<Widget> list = sut.getWidgetsList(factType);

        // Then
        assertThat(list)
                .containsExactly(expectedWidgetOne, expectedWidgetTwo);
    }
}
