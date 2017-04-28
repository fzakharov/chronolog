package com.revents.chronolog.features.statistics;


import com.revents.chronolog.app.*;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

public class StatMapWidgetFactory implements WidgetFactory<FactType> {
	private FactReader mFactReader;
	private DataContext mDataContext;
	private DateTimeProvider mDateTimeProv;

	public StatMapWidgetFactory(FactReader factReader, DataContext dataContext, DateTimeProvider dateTimeProv) {
		mFactReader = factReader;
		mDataContext = dataContext;
		mDateTimeProv = dateTimeProv;
	}

	@Override
	public Widget createWidget(String widgetName, FactType data) {

		if (widgetName.equals(MiddleCountPerDayWidget.class.getSimpleName()))
			return new MiddleCountPerDayWidget(data, mDataContext);

		if (widgetName.equals(MiddleRatingWidget.class.getSimpleName()))
			return new MiddleRatingWidget(data, mDataContext);

		if (widgetName.equals(MiddleRatingByWeekDaysWidget.class.getSimpleName()))
			return new MiddleRatingByWeekDaysWidget(data);

		throw new UnsupportedOperationException(widgetName);
	}
}
