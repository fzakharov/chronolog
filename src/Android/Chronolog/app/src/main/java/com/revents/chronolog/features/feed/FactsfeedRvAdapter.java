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

    public FactsfeedRvAdapter(List<Fact> facts) {
        mData = facts;
    }

    @Override
    public FactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fact_rv_item, parent, false);

        FactViewHolder ftvh = new FactViewHolder(v);

        v.setOnClickListener(ftvh);

        return ftvh;
    }

    @Override
    public void onBindViewHolder(FactViewHolder holder, int position) {
        Fact fact = mData.get(position);

        holder.setHeader(fact.getFactType().getName());
        holder.setValue(fact.getLongValue().toString());
        holder.setTime(mTimeFormat.format(fact.getFactDate()));
        holder.setWeekDay(mWeekFormat.format(fact.getFactDate()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class FactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View mView;

        FactViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        @Override
        public void onClick(View v) {

        }

        public void setHeader(String header) {
            setTv(R.id.headerTv, header);
        }

        public void setValue(String value) {
            setTv(R.id.valueTv, value);
        }

        public void setWeekDay(String weekDay) {
            setTv(R.id.weekDayTv, weekDay);
        }

        public void setTime(String time) {
            setTv(R.id.timeTv, time);
        }

        private void setTv(int id, String value) {
            TextView tv = (TextView) mView.findViewById(id);
            tv.setText(value);
        }
    }
}
