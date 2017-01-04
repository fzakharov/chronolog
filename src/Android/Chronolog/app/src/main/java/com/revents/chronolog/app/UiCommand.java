package com.revents.chronolog.app;

import android.app.Activity;

public interface UiCommand<T> extends ActivityResultHandler<T> {
    void execute(Activity activity);
}

