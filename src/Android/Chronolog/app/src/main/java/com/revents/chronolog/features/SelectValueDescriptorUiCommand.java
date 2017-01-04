package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

public class SelectValueDescriptorUiCommand extends ResultUiCommand<ValueDescriptor> {
    public static final int VALUE_DESCRIPTOR_ID_REQUEST_CODE = 400;

    public SelectValueDescriptorUiCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> extractor) {
        super(ValueDescriptorsActivity.class,
                VALUE_DESCRIPTOR_ID_REQUEST_CODE,
                extractor,
                intentFactory);
    }
}
