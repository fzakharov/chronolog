package com.revents.chronolog.features.group;

import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.model.FactTypeGroup;

public class NewFactTypeGroupResultUiCommand extends ResultResultUiCommand<FactTypeGroup> {
    public static final int NEW_FACT_TYPE_GROUP_REQUEST_CODE = 400;

    public NewFactTypeGroupResultUiCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        super(EditFactTypeGroupActivity.class,
                NEW_FACT_TYPE_GROUP_REQUEST_CODE,
                extractor,
                intentFactory);
    }
}
