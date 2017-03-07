package com.revents.chronolog.features.statistics;

import com.revents.chronolog.model.FactType;

public interface WidgetsRegistry<T> {
    String[] getActiveWidgets(T data);
}
