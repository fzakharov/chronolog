package com.revents.chronolog.features.feed;


import android.view.*;
import android.widget.*;

import java.text.*;

public class DateViewHolder extends FactsfeedViewHolder {

    private static final SimpleDateFormat mWeekFormat = new SimpleDateFormat("EEEE");
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    protected final View mView;

    DateViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mView.setLongClickable(false);
    }

    protected void setTv(int id, String value) {
        TextView tv = (TextView) mView.findViewById(id);
        tv.setText(value);
    }

    @Override
    public void bind(ItemPresenter item) {

    }
}
