package com.revents.chronolog.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface RecyclerViewBinder<TData> {
    int getResourceId(TData item);

    <TViewHolder extends RecyclerView.ViewHolder> TViewHolder createViewHolder(View view);
}
