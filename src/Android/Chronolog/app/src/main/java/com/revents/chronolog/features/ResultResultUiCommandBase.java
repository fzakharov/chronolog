package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import static android.app.Activity.RESULT_OK;

public abstract class ResultResultUiCommandBase<T> implements com.revents.chronolog.app.ResultUiCommand<T> {
    private IntentFactory mIntentFactory;

    protected ResultResultUiCommandBase(IntentFactory intentFactory) {
        mIntentFactory = intentFactory;
    }

    @Override
    public void execute(Activity current) {
        Intent intent = mIntentFactory.Create(current, getActivityClass());
        current.startActivityForResult(intent, getRequestCode());
    }

    protected abstract Class<?> getActivityClass();

    protected abstract int getRequestCode();

    protected abstract T extractResult(Intent data);

    @Override
    public T onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return null;

        if (requestCode != getRequestCode())
            return null;

        return extractResult(data);
    }
}
