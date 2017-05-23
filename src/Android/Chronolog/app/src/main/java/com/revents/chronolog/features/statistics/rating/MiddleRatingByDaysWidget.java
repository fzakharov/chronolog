package com.revents.chronolog.features.statistics.rating;


import com.revents.chronolog.app.*;
import com.revents.chronolog.features.statistics.*;
import com.revents.chronolog.model.FactType;

public class MiddleRatingByDaysWidget implements Widget {
    private FactType mFactType;
    private DataContext mDataContext;

    public MiddleRatingByDaysWidget(FactType factType, DataContext dataContext) {

        mFactType = factType;
        mDataContext = dataContext;
    }
}
