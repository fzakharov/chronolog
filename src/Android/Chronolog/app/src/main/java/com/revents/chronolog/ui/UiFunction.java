package com.revents.chronolog.ui;

import android.app.Activity;

public interface UiFunction<TIn, R> {
    R execute(Activity activity, TIn param);
}

