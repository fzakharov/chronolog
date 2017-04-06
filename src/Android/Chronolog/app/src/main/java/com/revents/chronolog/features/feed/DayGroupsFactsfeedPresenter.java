package com.revents.chronolog.features.feed;


import com.revents.chronolog.app.*;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.*;

import java.util.*;

public class DayGroupsFactsfeedPresenter implements FactsfeedPresenter {

	private FactReader mFactReader;
	private DateTimeProvider mDtProv;

	public DayGroupsFactsfeedPresenter(FactReader reader, DateTimeProvider dateTimeProvider) {

		mFactReader = reader;
		mDtProv = dateTimeProvider;
	}

	@Override
	public List<ItemPresenter> loadFactsfeed() {

		ArrayList<ItemPresenter> presenters = new ArrayList<>();

		for (Fact f : mFactReader.loadFactsfeed()) {
			add(f, presenters);
		}

		return presenters;
	}

	private void add(Fact f, ArrayList<ItemPresenter> p) {
		Date cur = f.getFactDate();
		Date prev = cur;

		int size = p.size();
		if (size > 0) {
			FactItemPresenter pip = (p.get(size - 1) instanceof FactItemPresenter ?
					(FactItemPresenter) p.get(size - 1) :
					null);

			prev = pip.getFact().getFactDate();
		} else {
			p.add(new FirstWeekItemPresenter(cur, mDtProv));
		}

		String prevWeek = mDtProv.toWeekDayString(prev);
		String curWeek = mDtProv.toWeekDayString(cur);

		if (!prevWeek.equals(curWeek))
			p.add(new FirstWeekItemPresenter(cur, mDtProv));

		p.add(new FactItemPresenter(f));
	}
}
