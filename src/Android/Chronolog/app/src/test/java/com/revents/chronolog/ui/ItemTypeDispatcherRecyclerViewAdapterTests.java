package com.revents.chronolog.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ximpleware.xpath.UnsupportedException;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemTypeDispatcherRecyclerViewAdapterTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    RecyclerViewBinder<String> mRvBinder;

    @Mock
    ArrayList<String> mDataSet;

    @InjectMocks
    ItemTypeDispatcherRecyclerViewAdapter<String, TestRvHolder> sut;

    @Test
    public void should_bind_item_at_pos_to_holder_When_onBindViewHolder() {
        // Given
        TestRvHolder holder = mock(TestRvHolder.class);
        int position = 42;

        // When
        sut.onBindViewHolder(holder, position);

        // Then

    }

    @Test
    public void should_inflate_holder_by_viewType_When_onCreateViewHolder() {
        // Given
        @LayoutRes final int viewType = 42;
        ViewGroup parent = mock(ViewGroup.class);
        TestRvHolder expectedHolder = mock(TestRvHolder.class);

        Context context = mock(Context.class);
        LayoutInflater inflater = mock(LayoutInflater.class);
        View view = mock(View.class);

        when(parent.getContext())
                .thenReturn(context);

        when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .thenReturn(inflater);

        when(inflater.inflate(viewType, parent, false))
                .thenReturn(view);

        when(mRvBinder.createViewHolder(view))
                .thenReturn(expectedHolder);

        // When
        TestRvHolder actual = sut.onCreateViewHolder(parent, viewType);

        // Then
        assertThat(actual).isEqualTo(expectedHolder);
    }

    @Test
    public void should_return_expected_resId_When_getItemViewType_by_position() {
        // Given
        int expectedResId = 5;
        int position = 42;
        String item = "item at 42";

        when(mDataSet.get(position))
                .thenReturn(item);

        when(mRvBinder.getResourceId(item))
                .thenReturn(expectedResId);

        // When
        int actual = sut.getItemViewType(position);

        // Then
        assertThat(actual).isEqualTo(expectedResId);
    }

    @Test
    public void should_return_count_from_dataSet_When_getItemCount() {
        // Given
        int expected = 100;
        when(mDataSet.size())
                .thenReturn(expected);

        // When
        int actual = sut.getItemCount();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private class TestRvHolder extends RecyclerView.ViewHolder {

        public TestRvHolder(View itemView) {
            super(itemView);
        }
    }
}
