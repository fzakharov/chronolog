package com.revents.chronolog.features.feed;


import com.revents.chronolog.model.*;

public class FactItemPresenter implements ItemPresenter{

	private Fact mFact;

	public FactItemPresenter(Fact fact){

		mFact = fact;
	}

	@Override
	public String getTitle() {
		return mFact.getFactType().getName();
	}

	public Fact getFact(){
		return mFact;
	}
}

