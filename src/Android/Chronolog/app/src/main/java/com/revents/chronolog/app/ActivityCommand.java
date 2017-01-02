package com.revents.chronolog.app;

import android.app.Activity;

public interface ActivityCommand<T> extends ActivityResultHandler<T> {
    void execute(Activity activity);
}

