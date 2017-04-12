package com.revents.chronolog.app;

import android.app.Activity;

public interface ParametrizedActivityCommand<TResult, TParam> extends ActivityResultHandler<TResult> {
    void execute(Activity activity, TParam parameter);
}
