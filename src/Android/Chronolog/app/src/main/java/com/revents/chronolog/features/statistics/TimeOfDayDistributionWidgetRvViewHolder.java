package com.revents.chronolog.features.statistics;


import android.graphics.*;
import android.view.*;
import android.widget.*;

import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.github.mikephil.charting.utils.*;
import com.revents.chronolog.*;

import java.util.*;

class TimeOfDayDistributionWidgetRvViewHolder extends WidgetRvViewHolder {
	private BarChart mChart;

	public TimeOfDayDistributionWidgetRvViewHolder(View view) {
		super(view);
	}

	@Override
	public void bind(Widget widget) {
		mChart = (BarChart) this.itemView.findViewById(R.id.barChart);

		TimeOfDayDistributionWidget w = (TimeOfDayDistributionWidget) widget;

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (int c : w.getCounts()) {
			yVals1.add(new BarEntry(
					(float) yVals1.size(),
					(float) c));
		}

		BarDataSet set1 = new BarDataSet(yVals1, "Часы");
		set1.setDrawValues(false);

		set1.setColor(R.color.colorPrimaryDark);

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set1);
		BarData data = new BarData(dataSets);
		mChart.setData(data);
	}
}
