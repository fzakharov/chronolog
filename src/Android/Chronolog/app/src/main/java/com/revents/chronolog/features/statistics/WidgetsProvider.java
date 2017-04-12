package com.revents.chronolog.features.statistics;

import java.util.List;

public interface WidgetsProvider<T> {
    List<Widget> getWidgetsList(T data);
}
