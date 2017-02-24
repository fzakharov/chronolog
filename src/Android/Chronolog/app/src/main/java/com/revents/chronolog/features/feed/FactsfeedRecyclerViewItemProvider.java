package com.revents.chronolog.features.feed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.ui.recyclerview.BindableHolder;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

import java.text.SimpleDateFormat;

public class FactsfeedRecyclerViewItemProvider implements RecyclerViewItemProvider<Fact, FactViewHolder> {

    @Override
    public int getResourceId(Fact item) {
        if (item.getFactType().getValueDescriptor().getClassName() == "rating") // TODO: 22.02.2017 rating to const
            return R.layout.rating_fact_rv_item;

        return R.layout.fact_rv_item;
    }

    @Override
    public FactViewHolder createViewHolder(View view, int resourceId) {
        if (resourceId == R.layout.rating_fact_rv_item) {
            return new RatingFactViewHolder(view);
        }

        return new FactViewHolder(view);
    }
}
