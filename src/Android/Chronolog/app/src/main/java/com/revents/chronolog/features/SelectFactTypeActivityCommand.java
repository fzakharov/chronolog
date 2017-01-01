package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;

public class SelectFactTypeActivityCommand implements ActivityCommand {

    private IntentFactory mIntentFactory;
    public static final int FACT_TYPE_ID_REQUEST_CODE = 1;

    public SelectFactTypeActivityCommand(IntentFactory mIntentFactory) {
        this.mIntentFactory = mIntentFactory;
    }

    @Override
    public void execute(Activity current) {
        Intent intent = mIntentFactory.Create(current, FactTypesActivity.class);
        current.startActivityForResult(intent, FACT_TYPE_ID_REQUEST_CODE);
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {
    }
}
