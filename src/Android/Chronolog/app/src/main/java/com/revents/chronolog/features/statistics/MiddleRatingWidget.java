package com.revents.chronolog.features.statistics;

import com.revents.chronolog.app.DateTimeProvider;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import java.util.Date;
import java.util.List;

public class MiddleRatingWidget implements Widget {
    private FactType mFactType;
    private FactReader mFactReader;
    private DateTimeProvider mDateTimeProv;
    public static final int DaysAgo = 30;

    public MiddleRatingWidget(FactType data, FactReader factReader, DateTimeProvider dateTimeProvider) {
        mFactType = data;
        mFactReader = factReader;
        mDateTimeProv = dateTimeProvider;
    }

    public float getMiddleRating() {
        return calculate();
    }

    private float calculate() {
        Date end = mDateTimeProv.getDate();
        Date begin = mDateTimeProv.getEndDaysAgo(end, DaysAgo);

        List<Fact> facts = mFactReader.loadFactsByType(mFactType, begin, end);

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

