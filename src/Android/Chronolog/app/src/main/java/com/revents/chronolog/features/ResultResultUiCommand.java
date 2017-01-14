package com.revents.chronolog.features;

import android.content.Intent;

public class ResultResultUiCommand<T> extends ResultUiCommandBase<T> {


    private Class<?> mActivityClass;
    private int mRequestCode;
    private IntentExtractor<T> mExtractor;

    public ResultResultUiCommand(
            Class<?> activity,
            int requestCode,
            IntentExtractor<T> extractor,
            IntentFactory intentFactory) {
        super(intentFactory);
        mActivityClass = activity;
        mRequestCode = requestCode;
        mExtractor = extractor;
    }

    @Override
    protected Class<?> getActivityClass() {
        return mActivityClass;
    }

    @Override
    protected int getRequestCode() {
        return mRequestCode;
    }

    @Override
    protected T extractResult(Intent data) {
        return mExtractor.extract(data);
    }
}
