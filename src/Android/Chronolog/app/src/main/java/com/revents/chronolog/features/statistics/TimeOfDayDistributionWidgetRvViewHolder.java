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

		int count = 5;
		float range = 5;

		float start = 1f;

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (int i = (int) start; i < start + count + 1; i++) {
			float mult = (range + 1);
			float val = (float) (Math.random() * mult);

			yVals1.add(new BarEntry(i, val));

		}

		BarDataSet set1;

		if (mChart.getData() != null &&
				mChart.getData().getDataSetCount() > 0) {
			set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
			set1.setValues(yVals1);
			mChart.getData().notifyDataChanged();
			mChart.notifyDataSetChanged();
		} else {
			set1 = new BarDataSet(yVals1, "The year 2017");
			set1.setColors(ColorTemplate.MATERIAL_COLORS);

			ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
			dataSets.add(set1);

			BarData data = new BarData(dataSets);
			data.setValueTextSize(10f);
			data.setBarWidth(0.9f);

			mChart.setData(data);
		}
	}
}
