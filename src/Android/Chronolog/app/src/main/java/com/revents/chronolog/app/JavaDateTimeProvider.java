package com.revents.chronolog.app;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaDateTimeProvider implements DateTimeProvider {
    SimpleDateFormat mDateFormat = new SimpleDateFormat("dd MMM yyyy");
    SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");

    @Override
    public Date getDate() {
        return new Date();
    }

    @Override
    public String toDateString(Date date) {
        return mDateFormat.format(date);
    }

    @Override
    public String toTimeString(Date date) {
        return mTimeFormat.format(date);
    }

    @Override
    public Date getEndDaysAgo(Date end, int daysAgo) {
        DateTime dateTime = new DateTime(end);
        return dateTime.minusDays(daysAgo).toDate();
    }
}
