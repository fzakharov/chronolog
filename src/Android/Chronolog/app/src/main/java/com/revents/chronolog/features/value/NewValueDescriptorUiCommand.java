package com.revents.chronolog.features.value;

import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultUiCommand;
import com.revents.chronolog.model.ValueDescriptor;

public class NewValueDescriptorUiCommand extends ResultUiCommand<ValueDescriptor> {
    public static final int NEW_VALUE_DESCRIPTOR_REQUEST_CODE = 500;

    public NewValueDescriptorUiCommand(IntentFactory intentFactory, IntentExtractor<ValueDescriptor> intentExtractor) {
        super(ValueTypesActivity.class,
                NEW_VALUE_DESCRIPTOR_REQUEST_CODE,
                intentExtractor,
                intentFactory);
    }
}
