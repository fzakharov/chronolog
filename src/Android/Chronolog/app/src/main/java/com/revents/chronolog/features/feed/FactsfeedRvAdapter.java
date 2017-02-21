package com.revents.chronolog.features.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.model.Fact;

import java.text.SimpleDateFormat;
import java.util.List;

public class FactsfeedRvAdapter extends RecyclerView.Adapter<FactsfeedRvAdapter.FactViewHolder> {
    private static final SimpleDateFormat mWeekFormat = new SimpleDateFormat("EEEE");
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm");
    private List<Fact> mData;
    private View.OnLongClickListener mLongClickListener;
    private View.OnClickListener mClickListener;

    public FactsfeedRvAdapter(List<Fact> facts, View.OnClickListener clickListener, View.OnLongClickListener longClickListener) {
        mData = facts;
        mLongClickListener = longClickListener;
        mClickListener = clickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return CellType.get(mData.get(position)).type();
    }

    @Override
    public FactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FactViewHolder holder = CellType.get(viewType).holder(parent, viewType);

        holder.setOnLongClickListener(mLongClickListener);
        holder.setOnClickListener(mClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(FactViewHolder holder, int position) {
        Fact fact = mData.get(position);
        CellType.get(fact).bind(holder, fact);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class FactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        protected final View mView;
        private View.OnLongClickListener mLongClickListener;
        private View.OnClickListener mClickListener;
        private Fact mFact;

        FactViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mView.setOnLongClickListener(this);
            mView.setOnClickListener(this);
            mView.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onClick(v);
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

        public void setOnClickListener(View.OnClickListener clickListener) {

            mClickListener = clickListener;
        }


        public Fact getFact() {
            return mFact;
        }

        public void setFact(Fact fact) {
            this.mFact = fact;

            setTv(R.id.headerTv, fact.getFactType().getName());
            setValue(fact.getLongValue());
            setTv(R.id.timeTv, mTimeFormat.format(fact.getFactDate()));
            setTv(R.id.weekDayTv, mWeekFormat.format(fact.getFactDate()));
        }

        protected void setValue(Long value) {
            setTv(R.id.valueTv, value.toString());
        }

        protected void setTv(int id, String value) {
            TextView tv = (TextView) mView.findViewById(id);
            tv.setText(value);
        }
    }

    public static class RatingFactViewHolder extends FactViewHolder {

        RatingFactViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void setValue(Long value) {
            ((RatingBar) mView.findViewById(R.id.ratingBar)).setRating(value);
        }
    }

    public enum CellType {
        RATING {
            @Override
            boolean is(Fact item) {
                return item.getFactType().getValueDescriptor().getClassName() == "rating";
            }

            @Override
            int type() {
                return R.layout.rating_fact_rv_item;
            }

            @Override
            FactViewHolder holder(ViewGroup parent, int type) {
                Context context = parent.getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(type, parent, false);
                return new RatingFactViewHolder(view);
            }

            @Override
            void bind(FactViewHolder holder, Fact item) {
                holder.setFact(item);
            }
        },
        DEFAULT {
            @Override
            boolean is(Fact item) {
                return item.getFactType().getValueDescriptor().getClassName() != "rating";
            }

            @Override
            int type() {
                return R.layout.fact_rv_item;
            }

            @Override
            FactViewHolder holder(ViewGroup parent, int type) {
                Context context = parent.getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(type, parent, false);
                return new FactViewHolder(view);
            }

            @Override
            void bind(FactViewHolder holder, Fact item) {
                holder.setFact(item);
            }
        };

        static CellType get(Fact item) {
            for (CellType cellType : CellType.values()) {
                if (cellType.is(item)) {
                    return cellType;
                }
            }
            throw new UnsupportedOperationException();
        }

        static CellType get(int viewType) {
            for (CellType cellType : CellType.values()) {
                if (cellType.type() == viewType) {
                    return cellType;
                }
            }
            throw new UnsupportedOperationException();
        }

        abstract boolean is(Fact item);

        abstract int type();

        abstract FactViewHolder holder(ViewGroup parent, int type);

        abstract void bind(FactViewHolder holder, Fact item);
    }
}
