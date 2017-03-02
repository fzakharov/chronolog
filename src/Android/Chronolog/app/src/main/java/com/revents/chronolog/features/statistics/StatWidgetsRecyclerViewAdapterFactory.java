package com.revents.chronolog.features.statistics;

import android.support.v7.widget.RecyclerView;

import com.revents.chronolog.app.RecyclerViewAdapterFactory;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.ui.recyclerview.ItemTypeDispatcherRecyclerViewAdapter;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

public class StatWidgetsRecyclerViewAdapterFactory implements RecyclerViewAdapterFactory<FactType> {

    private WidgetsProvider<FactType> mStatWidgetsProvider;
    private RecyclerViewItemProvider<Widget, WidgetRvViewHolder> mWidgetsRecyclerViewItemProvider;

    public StatWidgetsRecyclerViewAdapterFactory(
            WidgetsProvider<FactType> statWidgetsProvider,
            RecyclerViewItemProvider<Widget, WidgetRvViewHolder> widgetsRecyclerViewItemProvider) {

        mStatWidgetsProvider = statWidgetsProvider;
        mWidgetsRecyclerViewItemProvider = widgetsRecyclerViewItemProvider;
    }

    @Override
    public RecyclerView.Adapter create(FactType factType) {

        return new ItemTypeDispatcherRecyclerViewAdapter<>(
                mStatWidgetsProvider.getWidgetsList(factType),
                mWidgetsRecyclerViewItemProvider);
    }
}
