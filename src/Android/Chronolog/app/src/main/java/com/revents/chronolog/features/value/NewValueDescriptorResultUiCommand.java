package com.revents.chronolog.features.value;

import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.model.ValueDescriptor;

public class NewValueDescriptorResultUiCommand extends ResultResultUiCommand<ValueDescriptor> {
    public static final int NEW_VALUE_DESCRIPTOR_REQUEST_CODE = 500;

    public NewValueDescriptorResultUiCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> intentExtractor) {
        super(ValueTypesActivity.class,
                NEW_VALUE_DESCRIPTOR_REQUEST_CODE,
                intentExtractor,
                intentFactory);
    }
}
