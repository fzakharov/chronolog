package com.revents.chronolog.features.feed;


import com.revents.chronolog.app.*;

import java.util.*;

public class FirstWeekItemPresenter implements ItemPresenter {

	public static final String TODAY_STRING = "сегодня";
	private String mTitle;
	private Date mDate;
	private DateTimeProvider mDtProv;

	public FirstWeekItemPresenter(Date date, DateTimeProvider dateTimeProvider) {
		mDate = date;
		mDtProv = dateTimeProvider;
	}

	private void calcTitle() {
		Date dateDayPart = mDtProv.getDatePart(mDate);
		Date todayDayPart = mDtProv.getDatePart(mDtProv.getDate());

		if (dateDayPart.equals(todayDayPart))
			mTitle = TODAY_STRING;
		else
			mTitle = mDtProv.toFullDateStringWithWeekDay(mDate);
	}

	@Override
	public String getTitle() {
		if (mTitle == null)
			calcTitle();

		return mTitle;
	}
}
