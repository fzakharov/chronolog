package com.revents.chronolog.features.statistics;

import com.revents.chronolog.app.*;
import com.revents.chronolog.model.*;

public class TimeOfDayDistributionWidget implements Widget {
	private FactType mFactType;
	private DataContext mDataContext;

	public TimeOfDayDistributionWidget(FactType data, DataContext dataContext) {

		mFactType = data;
		mDataContext = dataContext;
	}
}
