package com.revents.chronolog.features.feed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.ui.recyclerview.BindableHolder;

import java.text.SimpleDateFormat;


public class FactViewHolder extends FactsfeedViewHolder {

    private static final SimpleDateFormat mWeekFormat = new SimpleDateFormat("EEEE");
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");

    protected final View mView;
    private Fact mFact;

    FactViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mView.setLongClickable(true);
    }

    public Fact getFact() {
        return mFact;
    }

    protected void setValue(Long value) {
        setTv(R.id.valueTv, value.toString());
    }

    protected void setTv(int id, String value) {
        TextView tv = (TextView) mView.findViewById(id);
        tv.setText(value);
    }

    @Override
    public void bind(ItemPresenter item) {
        if (item instanceof FactItemPresenter){
            mFact = ((FactItemPresenter)item).getFact();

            setTv(R.id.headerTv, mFact.getFactType().getName());
            setValue(mFact.getLongValue());
            setTv(R.id.timeTv, mTimeFormat.format(mFact.getFactDate()));
        }
    }
}
