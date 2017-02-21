package com.revents.chronolog.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ItemTypeDispatcherRecyclerViewAdapter<TData, TViewHolder extends RecyclerView.ViewHolder & BindableHolder<TData>>
        extends RecyclerView.Adapter<TViewHolder> {

    private ArrayList<TData> mDataSet;
    private RecyclerViewItemProvider<TData> mRecyclerViewItemProvider;

    public ItemTypeDispatcherRecyclerViewAdapter(ArrayList<TData> dataSet, RecyclerViewItemProvider<TData> recyclerViewItemProvider) {

        mDataSet = dataSet;
        mRecyclerViewItemProvider = recyclerViewItemProvider;
    }

    @Override
    public int getItemViewType(int position) {
        TData item = mDataSet.get(position);

        return mRecyclerViewItemProvider.getResourceId(item);
    }

    @Override
    public TViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(viewType, parent, false);

        return mRecyclerViewItemProvider.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
