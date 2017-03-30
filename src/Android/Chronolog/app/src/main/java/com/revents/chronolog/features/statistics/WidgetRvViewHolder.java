package com.revents.chronolog.features.statistics;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.revents.chronolog.ui.recyclerview.BindableHolder;

public abstract class WidgetRvViewHolder extends RecyclerView.ViewHolder implements BindableHolder<Widget> {
    public WidgetRvViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Widget widget);
}
