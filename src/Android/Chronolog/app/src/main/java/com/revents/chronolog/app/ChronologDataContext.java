package com.revents.chronolog.app;


import com.revents.chronolog.db.*;
import com.revents.chronolog.model.*;

import java.util.*;

public class ChronologDataContext implements DataContext {
	public static final int DEFAULT_PERIOD_DAYS = 14;
	private int mPeriodDays = DEFAULT_PERIOD_DAYS;
	private FactReader mFactReader;
	private DateTimeProvider mDateTimeProv;


	public ChronologDataContext(FactReader factReader, DateTimeProvider dateTimeProvider) {
		mFactReader = factReader;
		mDateTimeProv = dateTimeProvider;
	}

	@Override
	public void setPeriodDays(int days) {
		mPeriodDays = days;
	}

	@Override
	public int getPeriodDays() {
		return mPeriodDays;
	}

	@Override
	public List<Fact> getFactsByType(FactType factType) {
		Date end = mDateTimeProv.getDate();
		Date begin = mDateTimeProv.getEndDaysAgo(end, mPeriodDays);

		return mFactReader.loadFactsByType(factType, begin, end);
	}
}
