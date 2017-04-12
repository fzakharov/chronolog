package com.revents.chronolog.features.feed;


import android.view.*;
import android.widget.*;

import com.revents.chronolog.*;

import java.text.*;

public class DateViewHolder extends FactsfeedViewHolder {

	DateViewHolder(View itemView) {
		super(itemView);
	}

	@Override
	public void bind(ItemPresenter item) {
		FirstWeekItemPresenter p = (FirstWeekItemPresenter) item;

		setTv(R.id.valueTv, p.getTitle());
	}
}
