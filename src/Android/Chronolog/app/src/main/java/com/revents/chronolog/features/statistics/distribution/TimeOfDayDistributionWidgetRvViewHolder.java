package com.revents.chronolog.features.statistics.distribution;


import android.support.v4.content.res.*;
import android.view.*;

import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.revents.chronolog.*;
import com.revents.chronolog.features.statistics.*;
import com.revents.chronolog.features.statistics.distribution.*;

import java.util.*;

public class TimeOfDayDistributionWidgetRvViewHolder extends WidgetRvViewHolder {
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

		int colorChart = ResourcesCompat.getColor(itemView.getResources(), R.color.colorAccent, null);

		set1.setColor(colorChart);

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set1);
		BarData data = new BarData(dataSets);
		mChart.setData(data);
	}
}
