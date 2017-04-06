package com.revents.chronolog.features.feed;


import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.*;

import java.util.ArrayList;
import java.util.List;

public class DayGroupsFactsfeedPresenter implements FactsfeedPresenter {

	private FactReader mFactReader;

	public DayGroupsFactsfeedPresenter(FactReader reader) {

		mFactReader = reader;
	}

	@Override
	public List<ItemPresenter> loadFactsfeed() {

		ArrayList<ItemPresenter> presenters = new ArrayList<>();

		for (Fact f : mFactReader.loadFactsfeed()) {
			add(f, presenters);
		}

		return presenters;
	}

	private void add(Fact f, ArrayList<ItemPresenter> presenters) {
		presenters.add(new FactItemPresenter(f));
	}
}
