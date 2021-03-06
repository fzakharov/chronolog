package com.revents.chronolog.features.statistics;

import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

// TODO: 23.03.2017 Introduce map and coomon selector
public class StatRecyclerViewItemProvider implements RecyclerViewItemProvider<Widget, WidgetRvViewHolder> {
    @Override
    public int getResourceId(Widget item) {
        Class<? extends Widget> aClass = item.getClass();

        if (aClass == MiddleRatingWidget.class)
            return R.layout.middle_rating_widget_rv_item;

        if (aClass == MiddleRatingByWeekDaysWidget.class)
            return R.layout.middle_rating_by_week_days_widget_rv_item;

        throw new UnsupportedOperationException();
    }

    @Override
    public WidgetRvViewHolder createViewHolder(View view, int resourceId) {

        if (resourceId == R.layout.middle_rating_widget_rv_item)
            return new MiddleRatingWidgetRvViewHolder(view);

        if (resourceId == R.layout.middle_rating_by_week_days_widget_rv_item)
            return new MiddleRatingByWeekDaysWidgetRvViewHolder(view);

        throw new UnsupportedOperationException();
    }
}
