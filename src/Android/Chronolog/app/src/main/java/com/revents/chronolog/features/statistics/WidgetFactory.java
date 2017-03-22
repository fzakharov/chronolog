package com.revents.chronolog.features.statistics;

import com.revents.chronolog.model.FactType;

public interface WidgetFactory<T> {
    Widget createWidget(String widgetName, T data);
}

