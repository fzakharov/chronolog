package com.revents.chronolog.app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaDateTimeProvider implements DateTimeProvider {
    SimpleDateFormat mDateFormat = new SimpleDateFormat("dd MMM yyyy");

    @Override
    public Date getDate() {
        return new Date();
    }

    @Override
    public String toDateString(Date date) {
        return mDateFormat.format(date);
    }
}
