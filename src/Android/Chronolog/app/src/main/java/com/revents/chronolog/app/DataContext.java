package com.revents.chronolog.app;

import com.revents.chronolog.model.*;

import java.util.*;

public interface DataContext {

	void setPeriodDays(int days);
	int getPeriodDays();
	List<Fact> getFactsByType(FactType factType);
}

