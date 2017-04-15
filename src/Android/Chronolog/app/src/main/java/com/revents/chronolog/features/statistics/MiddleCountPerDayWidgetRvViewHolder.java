package com.revents.chronolog.features.statistics;


import android.view.*;
import android.widget.*;

import com.revents.chronolog.*;

import java.text.*;

public class MiddleCountPerDayWidgetRvViewHolder extends WidgetRvViewHolder {

	public MiddleCountPerDayWidgetRvViewHolder(View itemView) {
		super(itemView);
	}

	@Override
	public void bind(Widget widget) {
		TextView valueTv = (TextView) this.itemView.findViewById(R.id.valueTv);

		float middleCount = ((MiddleCountPerDayWidget) widget).getMiddleCount();
		valueTv.setText(new DecimalFormat("#.##").format(234.456f));
	}
}
