package com.revents.chronolog.features.value;

import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.model.ValueDescriptor;

public class SelectValueDescriptorResultUiCommand extends ResultResultUiCommand<ValueDescriptor> {
    public static final int VALUE_DESCRIPTOR_ID_REQUEST_CODE = 400;

    public SelectValueDescriptorResultUiCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> extractor) {
        super(ValueDescriptorsActivity.class,
                VALUE_DESCRIPTOR_ID_REQUEST_CODE,
                extractor,
                intentFactory);
    }
}
