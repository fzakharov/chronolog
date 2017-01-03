package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.model.FactTypeGroup;

public class SelectFactTypeGroupActivityCommand implements ActivityCommand<FactTypeGroup> {
    @Override
    public void execute(Activity activity) {

    }

    @Override
    public FactTypeGroup onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        return null;
    }
}
