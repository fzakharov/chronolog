package com.revents.chronolog.features.statistics;


import com.revents.chronolog.db.DbData;
import com.revents.chronolog.model.FactType;

public class HardCodedFactTypeWidgetsRegistry implements WidgetsRegistry<FactType> {
    @Override
    public String[] getActiveWidgets(FactType data) {
        if (data.getValueDescriptor().getClassName() == DbData.ClassNames.RATING)
            return new String[]{"MiddleRating", "MiddleRating", "MiddleRating", "MiddleRating"};

        return new String[0];
    }
}
