package com.revents.chronolog.features.feed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.ui.BindableHolder;
import com.revents.chronolog.ui.RecyclerViewItemProvider;

import java.text.SimpleDateFormat;

public class FactsfeedRecyclerViewItemProvider implements RecyclerViewItemProvider<Fact, FactsfeedRecyclerViewItemProvider.FactViewHolder> {

    private static final SimpleDateFormat mWeekFormat = new SimpleDateFormat("EEEE");
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");

    @Override
    public int getResourceId(Fact item) {
        if (item.getFactType().getValueDescriptor().getClassName() == "rating") // TODO: 22.02.2017 rating to const
            return R.layout.rating_fact_rv_item;

        return R.layout.fact_rv_item;
    }

    @Override
    public FactViewHolder createViewHolder(View view, int resourceId) {
        if (resourceId == R.layout.rating_fact_rv_item) {
            return new RatingFactViewHolder(view);
        }

        return new FactViewHolder(view);
    }

    public static class RatingFactViewHolder extends FactsfeedRecyclerViewItemProvider.FactViewHolder {

        RatingFactViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void setValue(Long value) {
            ((RatingBar) mView.findViewById(R.id.ratingBar)).setRating(value);
        }
    }

    public static class FactViewHolder extends RecyclerView.ViewHolder implements BindableHolder<Fact> {

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
        public void bind(Fact fact) {
            mFact = fact;

            setTv(R.id.headerTv, fact.getFactType().getName());
            setValue(fact.getLongValue());
            setTv(R.id.timeTv, mTimeFormat.format(fact.getFactDate()));
            setTv(R.id.weekDayTv, mWeekFormat.format(fact.getFactDate()));
        }
    }
}
