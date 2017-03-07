package com.revents.chronolog.features.statistics;

public interface WidgetFactory<T> {
    Widget createWidget(String widgetName, T data);
}
