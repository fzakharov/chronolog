package com.revents.chronolog.features.statistics;

import android.support.v7.widget.RecyclerView;

import com.revents.chronolog.app.RecyclerViewAdapterFactory;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.ui.recyclerview.ItemTypeDispatcherRecyclerViewAdapter;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

import java.util.List;

public class StatWidgetsRecyclerViewAdapterFactory implements RecyclerViewAdapterFactory<FactType> {

    private WidgetsListProvider mStatWidgetsProvider;
    private RecyclerViewItemProvider mWidgetsRecyclerViewItemProvider;

    public StatWidgetsRecyclerViewAdapterFactory(
            WidgetsListProvider statWidgetsProvider,
            RecyclerViewItemProvider widgetsRecyclerViewItemProvider) {

        mStatWidgetsProvider = statWidgetsProvider;
        mWidgetsRecyclerViewItemProvider = widgetsRecyclerViewItemProvider;
    }

    @Override
    public RecyclerView.Adapter create(FactType factType) {

        return new ItemTypeDispatcherRecyclerViewAdapter(
                mStatWidgetsProvider.getWidgetsList(factType),
                mWidgetsRecyclerViewItemProvider);
    }
}
