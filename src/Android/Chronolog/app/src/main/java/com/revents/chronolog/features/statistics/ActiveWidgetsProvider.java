package com.revents.chronolog.features.statistics;

import com.revents.chronolog.model.FactType;

import java.util.ArrayList;
import java.util.List;

public class ActiveWidgetsProvider<T> implements WidgetsProvider<T> {
    private WidgetFactory<T> mWidgetFactory;
    private WidgetsRegistry<T> mWidgetsRegistry;

    public ActiveWidgetsProvider(WidgetFactory<T> widgetFactory, WidgetsRegistry<T> widgetsRegistry) {
        mWidgetFactory = widgetFactory;
        mWidgetsRegistry = widgetsRegistry;
    }

    @Override
    public List<Widget> getWidgetsList(T data) {

        List<Widget> list = new ArrayList<>();

        String[] names = mWidgetsRegistry.getActiveWidgets(data);

        for(String widgetName: names) {

            Widget widget = mWidgetFactory.createWidget(widgetName, data);
            list.add(widget);
        }

        return  list;
    }
}
