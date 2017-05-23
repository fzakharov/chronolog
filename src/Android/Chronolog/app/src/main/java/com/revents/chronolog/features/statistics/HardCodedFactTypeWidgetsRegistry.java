package com.revents.chronolog.features.statistics;


import com.revents.chronolog.db.DbData;
import com.revents.chronolog.features.statistics.count.*;
import com.revents.chronolog.features.statistics.distribution.*;
import com.revents.chronolog.features.statistics.rating.*;
import com.revents.chronolog.model.FactType;

public class HardCodedFactTypeWidgetsRegistry implements WidgetsRegistry<FactType> {
	@Override
	public String[] getActiveWidgets(FactType data) {
		String className = data.getValueDescriptor().getClassName();

		switch (className) {
			case DbData.ClassNames.RATING:
				return new String[]{
						MiddleRatingByDaysWidget.class.getSimpleName(),
						MiddleRatingWidget.class.getSimpleName(),
						MiddleCountPerDayWidget.class.getSimpleName(),
						TimeOfDayDistributionWidget.class.getSimpleName()};

			case DbData.ClassNames.EVENT:
				return new String[]{
						MiddleCountPerDayWidget.class.getSimpleName(),
						TimeOfDayDistributionWidget.class.getSimpleName()
				};
		}

		return new String[0];
	}
}
