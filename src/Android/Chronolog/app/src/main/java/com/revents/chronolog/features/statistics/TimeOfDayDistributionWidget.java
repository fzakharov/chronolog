package com.revents.chronolog.features.statistics;

import android.support.v4.content.res.*;

import com.revents.chronolog.app.*;
import com.revents.chronolog.model.*;

import org.joda.time.*;

import java.util.*;

public class TimeOfDayDistributionWidget implements Widget {
	private FactType mFactType;
	private DataContext mDataContext;

	public TimeOfDayDistributionWidget(FactType factType, DataContext dataContext) {

		mFactType = factType;
		mDataContext = dataContext;
	}

	public int[] getCounts() {

		int[] counts = new int[24];

		for (Fact f : mDataContext.getFactsByType(mFactType)) {
			Date factDate = f.getFactDate();
			// TODO: 06.05.2017 Convert using joda
			int h = factDate.getHours();
			int m = factDate.getMinutes();
			if (m > 30)
				h++;

			if (h == 24)
				h = 0;

			counts[h]++;
		}

		return counts;
	}
}
