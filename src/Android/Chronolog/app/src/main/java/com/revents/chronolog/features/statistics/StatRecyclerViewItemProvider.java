package com.revents.chronolog.features.statistics;

import android.view.View;

import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

public class StatRecyclerViewItemProvider implements RecyclerViewItemProvider<Widget,WidgetRvViewHolder> {
    @Override
    public int getResourceId(Widget item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WidgetRvViewHolder createViewHolder(View view, int resourceId) {
        return null;
    }
}
