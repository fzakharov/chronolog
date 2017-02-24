package com.revents.chronolog.features.feed;

import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.ValueDescriptor;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FactsfeedRecyclerViewItemProviderTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    FactsfeedRecyclerViewItemProvider sut;

    @Test
    public void should_return_rating_view_holder_When_createViewHolder() {
        // Given

        // When
        FactViewHolder holder = sut.createViewHolder(mock(View.class), R.layout.rating_fact_rv_item);

        // Then
        assertThat(holder).isInstanceOf(RatingFactViewHolder.class);
    }

    @Test
    public void should_return_rating_layout_When_getResourceId() {
        should_return_layout_by_value_descriptor_class_When_getResourceId("rating", R.layout.rating_fact_rv_item);
    }

    @Test
    public void should_return_default_fact_layout_When_getResourceId() {
        should_return_layout_by_value_descriptor_class_When_getResourceId("default", R.layout.fact_rv_item);
    }

    private void should_return_layout_by_value_descriptor_class_When_getResourceId(String className, int resId) {
        // Given
        Fact f = mock(Fact.class);
        FactType ft = mock(FactType.class);
        ValueDescriptor vd = mock(ValueDescriptor.class);

        when(f.getFactType()).thenReturn(ft);
        when(ft.getValueDescriptor()).thenReturn(vd);
        when(vd.getClassName()).thenReturn(className);

        // When
        int id = sut.getResourceId(f);

        // Then
        assertThat(id).isEqualTo(resId);
    }
}
