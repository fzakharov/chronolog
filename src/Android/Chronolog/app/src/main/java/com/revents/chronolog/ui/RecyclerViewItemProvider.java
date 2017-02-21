package com.revents.chronolog.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface RecyclerViewItemProvider<TData> {
    int getResourceId(TData item);

    <TViewHolder extends RecyclerView.ViewHolder & BindableHolder> TViewHolder createViewHolder(View view, int resourceId);
}
