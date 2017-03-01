package com.revents.chronolog.features.statistics;


import com.revents.chronolog.model.FactType;

import java.util.List;

public interface WidgetsListProvider {
    List getWidgetsList(FactType factType);
}
