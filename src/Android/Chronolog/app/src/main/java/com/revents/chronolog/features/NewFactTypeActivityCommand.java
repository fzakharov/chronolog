package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.model.FactType;

public class NewFactTypeActivityCommand implements ActivityCommand<FactType> {
    @Override
    public void execute(Activity activity) {

    }

    @Override
    public FactType onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        return null;
    }
}
