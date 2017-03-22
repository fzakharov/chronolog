package com.revents.chronolog.features.statistics;

import android.view.View;
import android.widget.RatingBar;

import com.revents.chronolog.R;

public class MiddleRatingWidgetRvViewHolder extends WidgetRvViewHolder {
    public MiddleRatingWidgetRvViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Widget widget) {
        RatingBar ratingBar = (RatingBar) this.itemView.findViewById(R.id.ratingBar);

        ratingBar.setIsIndicator(true);
        ratingBar.setRating(3.5f);
    }
}

