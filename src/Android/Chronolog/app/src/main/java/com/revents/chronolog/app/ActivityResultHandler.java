package com.revents.chronolog.app;

import android.app.Activity;
import android.content.Intent;

public interface ActivityResultHandler<T>
{
    T onResult(Activity activity, int requestCode, int resultCode, Intent data);
}
