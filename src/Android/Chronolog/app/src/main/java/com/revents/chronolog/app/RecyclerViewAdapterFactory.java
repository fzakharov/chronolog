package com.revents.chronolog.app;

import android.support.v7.widget.RecyclerView;

public interface RecyclerViewAdapterFactory<T> {
    RecyclerView.Adapter create(T dataSet);
}
