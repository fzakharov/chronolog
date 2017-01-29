package com.revents.chronolog.features.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.model.Fact;

import java.text.SimpleDateFormat;
import java.util.List;

public class FactsfeedRvAdapter extends RecyclerView.Adapter<FactsfeedRvAdapter.FactViewHolder> {
    private final SimpleDateFormat mWeekFormat = new SimpleDateFormat("EEEE");
    private final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");
    private List<Fact> mData;
    private View.OnLongClickListener mLongClickListener;

    public FactsfeedRvAdapter(List<Fact> facts, View.OnLongClickListener longClickListener) {
        mData = facts;
        mLongClickListener = longClickListener;
    }

    @Override
    public FactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fact_rv_item, parent, false);

        FactViewHolder ftvh = new FactViewHolder(v);
        v.setOnClickListener(ftvh);
        v.setOnLongClickListener(ftvh);

        ftvh.setOnLongClickListener(mLongClickListener);
        v.setTag(ftvh);

        return ftvh;
    }

    @Override
    public void onBindViewHolder(FactViewHolder holder, int position) {
        Fact fact = mData.get(position);
        holder.setFact(fact);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class FactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final View mView;
        private View.OnLongClickListener mLongClickListener;
        private Fact mFact;

        FactViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            if (mLongClickListener == null)
                return false;

            mLongClickListener.onLongClick(v);

            return true;
        }

        public void setOnLongClickListener(View.OnLongClickListener longClickListener) {

            mLongClickListener = longClickListener;
        }

        public Fact getFact() {
            return mFact;
        }

        public void setFact(Fact fact) {
            this.mFact = fact;

            setTv(R.id.headerTv, fact.getFactType().getName());
            setTv(R.id.valueTv, fact.getLongValue().toString());
            setTv(R.id.timeTv, mTimeFormat.format(fact.getFactDate()));
            setTv(R.id.weekDayTv, mWeekFormat.format(fact.getFactDate()));
        }

        private void setTv(int id, String value) {
            TextView tv = (TextView) mView.findViewById(id);
            tv.setText(value);
        }
    }
}
