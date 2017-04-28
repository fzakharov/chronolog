package com.revents.chronolog.features.statistics;

import com.revents.chronolog.app.*;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import java.util.Date;
import java.util.List;

public class MiddleRatingWidget implements Widget {
    private FactType mFactType;
    private DataContext mDataContext;

    public MiddleRatingWidget(FactType data, DataContext dataContext) {
        mFactType = data;
        mDataContext = dataContext;
    }

    public float getMiddleRating() {
        return calculate();
    }

    private float calculate() {
        List<Fact> facts = mDataContext.getFactsByType(mFactType);

        if (facts.size() == 0)
            return 0;

        float sum = 0;
        int size = facts.size();
        for (int i = 0; i < size; ++i) {
            sum += facts.get(i).getLongValue();
        }

        return sum / size;
    }
}

