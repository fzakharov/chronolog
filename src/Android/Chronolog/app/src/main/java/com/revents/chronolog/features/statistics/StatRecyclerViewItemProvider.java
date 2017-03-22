package com.revents.chronolog.features.statistics;

import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

public class StatRecyclerViewItemProvider implements RecyclerViewItemProvider<Widget, WidgetRvViewHolder> {
    @Override
    public int getResourceId(Widget item) {
        if (item.getClass() == MiddleRatingWidget.class)
            return R.layout.middle_rating_widget_rv_item;

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
