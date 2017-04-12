package com.revents.chronolog.app;

import android.content.Context;
import android.util.Pair;

public interface YesNoDialog {
    <T> void show(
            Context context,
            T data,
            String msg,
            final EventListener<EventArgs<Pair<Boolean, T>>> listener);
}
