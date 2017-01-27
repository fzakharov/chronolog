package com.revents.chronolog.app;

import android.content.Context;

import java.util.Date;

public interface TimeDialog {
    void show(Date selectedDate, Context context, DateListener listener);
}
