package com.revents.chronolog.features.statistics;

import android.support.v7.widget.RecyclerView;

import com.revents.chronolog.model.FactType;
import com.revents.chronolog.ui.recyclerview.ItemTypeDispatcherRecyclerViewAdapter;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatWidgetsRecyclerViewAdapterFactoryTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private StatWidgetsRecyclerViewAdapterFactory sut;

    @Mock
    private WidgetsListProvider mStatWidgetsListProvider;

    @Mock
    private RecyclerViewItemProvider mWidgetsRecyclerViewItemProvider;

    private FactType mTestFactType = mock(FactType.class);


    @Test
    public void should_provide_WidgetsRecyclerViewItemProvider_When_create() {
        // Given
        int position = 1;
        Object[] widgets = new Object[position + 1];
        widgets[position] = new Object();

        when(mStatWidgetsListProvider.getWidgetsList(mTestFactType))
                .thenReturn(new ArrayList<>(Arrays.asList(widgets)));

        int expected = 555;
        when(mWidgetsRecyclerViewItemProvider.getResourceId(widgets[position]))
                .thenReturn(expected);

        RecyclerView.Adapter adapter = sut.create(mTestFactType);

        // When
        int actual = adapter.getItemViewType(position);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_provide_widgets_list_by_factType_When_create() {
        // Given
        int expectedCount = 42;
        Object[] widgets = new Object[expectedCount];

        when(mStatWidgetsListProvider.getWidgetsList(mTestFactType))
                .thenReturn(new ArrayList<>(Arrays.asList(widgets)));

        // When
        RecyclerView.Adapter adapter = sut.create(mTestFactType);

        // Then
        assertThat(adapter.getItemCount()).isEqualTo(expectedCount);
    }

    @Test
    public void should_create_ItemTypeDispatcherRecyclerViewAdapter_When_create() {
        // Given
        // When
        RecyclerView.Adapter actual = sut.create(mTestFactType);

        // Then
        assertThat(actual).isInstanceOf(ItemTypeDispatcherRecyclerViewAdapter.class);
    }
}
