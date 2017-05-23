package com.revents.chronolog.features.statistics.rating;

import android.view.View;
import android.widget.RatingBar;

import com.revents.chronolog.R;
import com.revents.chronolog.features.statistics.*;

public class MiddleRatingWidgetRvViewHolder extends WidgetRvViewHolder {
	public MiddleRatingWidgetRvViewHolder(View itemView) {
		super(itemView);
	}

	@Override
	public void bind(Widget widget) {
		RatingBar ratingBar = (RatingBar) this.itemView.findViewById(R.id.ratingBar);

		ratingBar.setIsIndicator(true);
		ratingBar.setRating(((MiddleRatingWidget) widget).getMiddleRating());
	}
}

