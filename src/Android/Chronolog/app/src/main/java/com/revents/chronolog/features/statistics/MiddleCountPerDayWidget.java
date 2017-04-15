package com.revents.chronolog.features.statistics;


import com.revents.chronolog.app.*;
import com.revents.chronolog.db.*;
import com.revents.chronolog.model.*;

import java.util.*;

public class MiddleCountPerDayWidget implements Widget{
	private FactType mFactType;
	private FactReader mFactReader;
	private DateTimeProvider mDateTimeProv;
	public static final int DaysAgo = 30;

	public MiddleCountPerDayWidget(FactType data, FactReader factReader, DateTimeProvider dateTimeProvider) {
		mFactType = data;
		mFactReader = factReader;
		mDateTimeProv = dateTimeProvider;
	}

	public float getMiddleCount() {
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

		return sum / DaysAgo;
	}
}
