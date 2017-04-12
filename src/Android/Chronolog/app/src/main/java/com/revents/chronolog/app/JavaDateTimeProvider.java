package com.revents.chronolog.app;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaDateTimeProvider implements DateTimeProvider {
	SimpleDateFormat mDateFormat = new SimpleDateFormat("dd MMM yyyy");
	SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");
	SimpleDateFormat mWeekFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");

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

	@Override
	public String toFullDateStringWithWeekDay(Date date) {
		return mWeekFormat.format(date);
	}

	@Override
	public Date getDatePart(Date date) {
		DateTime jdt = new DateTime(date);
		return jdt.toLocalDate().toDate();
	}
}
