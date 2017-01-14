package com.revents.chronolog.app;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.features.IntentFactory;

public class DefaultIntentFactory implements IntentFactory {
    @Override
    public Intent Create(Activity activity, Class<?> activityClass) {
        return new Intent(activity, activityClass);
    }
}
