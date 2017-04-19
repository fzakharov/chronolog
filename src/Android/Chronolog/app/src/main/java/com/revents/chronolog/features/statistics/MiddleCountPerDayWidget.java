package com.revents.chronolog.features.statistics;


import com.revents.chronolog.app.*;
import com.revents.chronolog.db.*;
import com.revents.chronolog.model.*;

import java.util.*;

public class MiddleCountPerDayWidget implements Widget {
	private FactType mFactType;
	private DataContext mDataContext;

	public MiddleCountPerDayWidget(FactType data, DataContext dataContext) {
		mFactType = data;
		mDataContext = dataContext;
	}

	public float getMiddleCount() {
		return calculate();
	}

	private float calculate() {
		List<Fact> facts = mDataContext.getFactsByType(mFactType);

		if (facts.size() == 0)
			return 0;

		int size = facts.size();
		return (float) size / (float) mDataContext.getPeriodDays();
	}
}
