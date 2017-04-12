package com.revents.chronolog.app;

import java.util.Date;

public interface DateTimeProvider {
    Date getDate();

    String toDateString(Date date);

    String toTimeString(Date newDate);

    Date getEndDaysAgo(Date end, int daysAgo);

	String toFullDateStringWithWeekDay(Date date);

	Date getDatePart(Date date);
}
