package com.revents.chronolog.features.value;

import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultUiCommand;
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
