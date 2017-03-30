package com.revents.chronolog.ui;

import android.app.Activity;

public interface UiAction<TIn>{
    void execute(Activity activity, TIn param);
}