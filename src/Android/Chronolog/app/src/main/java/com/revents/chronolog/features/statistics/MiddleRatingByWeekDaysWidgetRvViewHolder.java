package com.revents.chronolog.features.statistics;


import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.revents.chronolog.R;

public class MiddleRatingByWeekDaysWidgetRvViewHolder extends WidgetRvViewHolder {
    public MiddleRatingByWeekDaysWidgetRvViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Widget widget) {
        Button btn = (Button) this.itemView.findViewById(R.id.button);

        btn.setText("By week days");
    }
}
