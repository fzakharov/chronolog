package com.revents.chronolog.features.statistics.rating;


import android.support.v4.content.res.*;
import android.view.View;

import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.formatter.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.revents.chronolog.R;
import com.revents.chronolog.app.*;
import com.revents.chronolog.features.statistics.*;
import com.revents.chronolog.model.*;

import org.joda.time.*;

import java.util.*;

import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.view.*;

public class MiddleRatingByDaysWidgetRvViewHolder extends WidgetRvViewHolder {
	//private final DataContext mDataContext;
	//private BarChart mChart;
	private ColumnChartView mChart;


	public MiddleRatingByDaysWidgetRvViewHolder(View itemView) {
		super(itemView);
		//mDataContext = dataContext;
	}

	@Override
	public void bind(Widget widget) {
		mChart = (ColumnChartView) this.itemView.findViewById(R.id.columnChart);
		MiddleRatingByDaysWidget w = (MiddleRatingByDaysWidget) widget;

		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;

		int colorChart = ResourcesCompat.getColor(itemView.getResources(), R.color.colorAccent, null);

		for (Fact f : w.getFacts()) {
			DateTime dt = new DateTime(f.getFactDate());

			values = new ArrayList<>();
			values.add(new SubcolumnValue((float) f.getLongValue(), colorChart));

			Column column = new Column(values);
			column.setHasLabels(false);
			column.setHasLabelsOnlyForSelected(true);
			columns.add(column);
		}

		ColumnChartData data = new ColumnChartData(columns);

		mChart.setColumnChartData(data);
	}

//	@Override
//	public void bind(Widget widget) {
//		mChart = (BarChart) this.itemView.findViewById(R.id.barChart);
//
//		mChart.setDrawGridBackground(false);
//		mChart.getDescription().setEnabled(false);
//		mChart.setDrawGridBackground(false);
//
//		MiddleRatingByDaysWidget w = (MiddleRatingByDaysWidget) widget;
//
//		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//
//		for (Fact f : w.getFacts()) {
//			DateTime dt = new DateTime(f.getFactDate());
//
//			//float x = dt.getMillis()/100000;
//			float x = f.getId();
//			float y = f.getLongValue();
//			yVals1.add(new BarEntry(x, y));
//		}
//
//
//		BarDataSet set1 = new BarDataSet(yVals1, "Дни");
//		set1.setDrawValues(false);
//		set1.setColor(R.color.colorPrimaryDark);
//
//		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
//		dataSets.add(set1);
//		BarData data = new BarData(dataSets);
//		mChart.setData(data);
//	}
}
