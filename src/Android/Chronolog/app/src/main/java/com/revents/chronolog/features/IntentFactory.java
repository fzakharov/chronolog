package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

public interface IntentFactory {
    Intent Create(Activity activity, Class<?> activityClass);
}
