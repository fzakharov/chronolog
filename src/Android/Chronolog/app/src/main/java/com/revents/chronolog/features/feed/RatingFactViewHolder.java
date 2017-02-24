package com.revents.chronolog.features.feed;

import android.view.View;
import android.widget.RatingBar;

import com.revents.chronolog.R;

public class RatingFactViewHolder extends FactViewHolder {

    RatingFactViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void setValue(Long value) {
        ((RatingBar) mView.findViewById(R.id.ratingBar)).setRating(value);
    }
}
