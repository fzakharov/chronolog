package com.revents.chronolog.features.statistics;


import com.revents.chronolog.model.FactType;

public class StatMapWidgetFactory implements WidgetFactory<FactType> {
    @Override
    public Widget createWidget(String widgetName, FactType data) {

        if (widgetName == "MiddleRating")
            return new MiddleRatingWidget(data);

        throw new UnsupportedOperationException(widgetName);
    }
}
