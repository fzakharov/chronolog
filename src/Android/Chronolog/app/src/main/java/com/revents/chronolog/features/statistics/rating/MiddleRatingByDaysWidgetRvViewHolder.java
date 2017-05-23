package com.revents.chronolog.features.statistics.rating;


import android.view.View;

import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.revents.chronolog.R;
import com.revents.chronolog.features.statistics.*;

import java.util.*;

public class MiddleRatingByDaysWidgetRvViewHolder extends WidgetRvViewHolder {
	private BarChart mChart;

	public MiddleRatingByDaysWidgetRvViewHolder(View itemView) {
		super(itemView);
	}

	@Override
	public void bind(Widget widget) {
		mChart = (BarChart) this.itemView.findViewById(R.id.barChart);

		MiddleRatingByDaysWidget w = (MiddleRatingByDaysWidget) widget;

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		// TODO: 23.05.2017 Generate test data to prototype view

//        for (int c : w.getRatings()) {
//            yVals1.add(new BarEntry(
//                    (float) yVals1.size(),
//                    (float) c));
//        }

		BarDataSet set1 = new BarDataSet(yVals1, "Дни");
		set1.setDrawValues(false);

		set1.setColor(R.color.colorPrimaryDark);

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set1);
		BarData data = new BarData(dataSets);
		mChart.setData(data);
	}
}
