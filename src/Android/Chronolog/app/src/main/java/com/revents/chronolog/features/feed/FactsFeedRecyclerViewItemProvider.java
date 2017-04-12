package com.revents.chronolog.features.feed;

import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.model.*;
import com.revents.chronolog.ui.recyclerview.RecyclerViewItemProvider;

public class FactsfeedRecyclerViewItemProvider implements RecyclerViewItemProvider<ItemPresenter, FactsfeedViewHolder> {

	@Override
	public int getResourceId(ItemPresenter item) {
		if (item instanceof FactItemPresenter) {
			String className = ((FactItemPresenter) item).getFact()
					.getFactType()
					.getValueDescriptor()
					.getClassName();

			if (className == "rating") // TODO: 22.02.2017 rating to const
				return R.layout.rating_fact_rv_item;

			return R.layout.fact_rv_item;
		}

		return R.layout.date_rv_item;
	}

	@Override
	public FactsfeedViewHolder createViewHolder(View view, int resourceId) {
		if (resourceId == R.layout.rating_fact_rv_item) {
			return new RatingFactViewHolder(view);
		}
		else if (resourceId == R.layout.fact_rv_item)
			return new FactViewHolder(view);

		return new DateViewHolder(view);
	}
}
