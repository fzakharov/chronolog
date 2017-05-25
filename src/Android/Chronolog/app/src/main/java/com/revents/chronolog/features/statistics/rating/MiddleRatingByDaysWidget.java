package com.revents.chronolog.features.statistics.rating;


import com.revents.chronolog.app.*;
import com.revents.chronolog.features.statistics.*;
import com.revents.chronolog.model.*;

import java.util.*;

public class MiddleRatingByDaysWidget implements Widget {
    private FactType mFactType;
    private DataContext mDataContext;

    public MiddleRatingByDaysWidget(FactType factType, DataContext dataContext) {

        mFactType = factType;
        mDataContext = dataContext;
    }

    public List<Fact> getFacts(){
        return mDataContext.getFactsByType(mFactType);
    }
}
