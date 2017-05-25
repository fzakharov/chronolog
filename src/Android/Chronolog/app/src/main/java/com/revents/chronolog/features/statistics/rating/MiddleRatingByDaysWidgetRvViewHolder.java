package com.revents.chronolog.features.statistics.rating;


import android.view.View;

import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.formatter.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.revents.chronolog.R;
import com.revents.chronolog.features.statistics.*;
import com.revents.chronolog.model.*;

import org.joda.time.*;

import java.util.*;

public class MiddleRatingByDaysWidgetRvViewHolder extends WidgetRvViewHolder {
	private BarChart mChart;

	public MiddleRatingByDaysWidgetRvViewHolder(View itemView) {
		super(itemView);
	}

	@Override
	public void bind(Widget widget) {
		mChart = (BarChart) this.itemView.findViewById(R.id.barChart);

		mChart.setDrawGridBackground(false);
		mChart.getDescription().setEnabled(false);
		mChart.setDrawGridBackground(false);

		MiddleRatingByDaysWidget w = (MiddleRatingByDaysWidget) widget;


		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		//xAxis.setTypeface(mTfLight);
		xAxis.setDrawGridLines(false);
		xAxis.setGranularity(1f); // only intervals of 1 day
		xAxis.setLabelCount(7);

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (Fact f : w.getFacts()) {
			DateTime dt = new DateTime(f.getFactDate());

			float x = f.getId();
			float y = f.getLongValue();
			yVals1.add(new BarEntry(x, y));
		}


		BarDataSet set1 = new BarDataSet(yVals1, "Дни");
		set1.setDrawValues(false);

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set1);
		BarData data = new BarData(dataSets);
		mChart.setData(data);
	}
}
