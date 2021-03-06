package com.revents.chronolog.features.statistics;


import com.revents.chronolog.app.DateTimeProvider;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

public class StatMapWidgetFactory implements WidgetFactory<FactType> {
    private FactReader mFactReader;
    private DateTimeProvider mDateTimeProv;

    public StatMapWidgetFactory(FactReader factReader, DateTimeProvider dateTimeProv) {
        mFactReader = factReader;
        mDateTimeProv = dateTimeProv;
    }

    @Override
    public Widget createWidget(String widgetName, FactType data) {

        if (widgetName == "MiddleRating")
            return new MiddleRatingWidget(data, mFactReader, mDateTimeProv);

        if (widgetName == "MiddleRatingByWeekDays")
            return new MiddleRatingByWeekDaysWidget(data);

        throw new UnsupportedOperationException(widgetName);
    }
}
