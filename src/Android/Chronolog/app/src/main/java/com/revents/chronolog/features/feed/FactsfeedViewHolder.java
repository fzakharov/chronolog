package com.revents.chronolog.features.feed;


import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import com.revents.chronolog.ui.recyclerview.*;

public abstract class FactsfeedViewHolder extends RecyclerView.ViewHolder implements BindableHolder<ItemPresenter> {

	protected final View mView;

	protected FactsfeedViewHolder(View itemView) {
		super(itemView);
		mView = itemView;
		mView.setLongClickable(true);
	}

	protected void setTv(int id, String value) {
		TextView tv = (TextView) mView.findViewById(id);
		tv.setText(value);
	}

	public abstract void bind(ItemPresenter item);
}
