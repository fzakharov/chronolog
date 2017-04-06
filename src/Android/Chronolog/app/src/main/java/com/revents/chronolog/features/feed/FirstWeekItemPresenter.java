package com.revents.chronolog.features.feed;


import com.revents.chronolog.app.*;

import java.util.*;

public class FirstWeekItemPresenter implements ItemPresenter{

	private String mTitle;

	public FirstWeekItemPresenter(Date date, DateTimeProvider dateTimeProvider) {
		mTitle = dateTimeProvider.toWeekDayString(date);
	}

	@Override
	public String getTitle() {
		return mTitle;
	}
}
