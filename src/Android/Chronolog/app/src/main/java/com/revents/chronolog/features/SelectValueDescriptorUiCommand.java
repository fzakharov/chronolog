package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.model.ValueDescriptor;

public class SelectValueDescriptorUiCommand implements UiCommand<ValueDescriptor> {
    @Override
    public void execute(Activity activity) {

    }

    @Override
    public ValueDescriptor onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        return null;
    }
}
