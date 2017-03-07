package com.revents.chronolog.features.statistics;

import com.revents.chronolog.model.FactType;

import java.util.ArrayList;
import java.util.List;

public class StatWidgetsProvider implements WidgetsProvider<FactType> {
    private WidgetFactory<FactType> mWidgetFactory;
    private WidgetsRegistry<FactType> mWidgetsRegistry;

    public StatWidgetsProvider(WidgetFactory<FactType> widgetFactory, WidgetsRegistry<FactType> widgetsRegistry) {
        mWidgetFactory = widgetFactory;
        mWidgetsRegistry = widgetsRegistry;
    }

    @Override
    public List<Widget> getWidgetsList(FactType data) {

        List<Widget> list = new ArrayList<>();

        String[] names = mWidgetsRegistry.getActiveWidgets(data);

        for(String widgetName: names) {

            Widget widget = mWidgetFactory.createWidget(widgetName, data);
            list.add(widget);
        }

        return  list;
    }
}
