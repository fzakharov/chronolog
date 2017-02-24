package com.revents.chronolog.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface RecyclerViewItemProvider<TData, TViewHolder extends RecyclerView.ViewHolder & BindableHolder> {
    int getResourceId(TData item);

    TViewHolder createViewHolder(View view, int resourceId);
}
