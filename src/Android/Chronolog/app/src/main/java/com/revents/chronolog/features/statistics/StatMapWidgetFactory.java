package com.revents.chronolog.features.statistics;


import com.revents.chronolog.app.*;
import com.revents.chronolog.features.statistics.count.*;
import com.revents.chronolog.features.statistics.distribution.*;
import com.revents.chronolog.features.statistics.rating.*;
import com.revents.chronolog.model.FactType;

public class StatMapWidgetFactory implements WidgetFactory<FactType> {
	private DataContext mDataContext;

	public StatMapWidgetFactory(DataContext dataContext) {
		mDataContext = dataContext;
	}

	@Override
	public Widget createWidget(String widgetName, FactType data) {

		if (widgetName.equals(MiddleCountPerDayWidget.class.getSimpleName()))
			return new MiddleCountPerDayWidget(data, mDataContext);

		if (widgetName.equals(MiddleRatingWidget.class.getSimpleName()))
			return new MiddleRatingWidget(data, mDataContext);

		if (widgetName.equals(MiddleRatingByDaysWidget.class.getSimpleName()))
			return new MiddleRatingByDaysWidget(data, mDataContext);

		if (widgetName.equals(TimeOfDayDistributionWidget.class.getSimpleName()))
			return new TimeOfDayDistributionWidget(data, mDataContext);

		throw new UnsupportedOperationException(widgetName);
	}
}
