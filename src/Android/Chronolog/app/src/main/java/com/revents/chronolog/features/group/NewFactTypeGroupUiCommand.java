package com.revents.chronolog.features.group;

import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultUiCommand;
import com.revents.chronolog.features.group.EditFactTypeGroupActivity;
import com.revents.chronolog.model.FactTypeGroup;

public class NewFactTypeGroupUiCommand extends ResultUiCommand<FactTypeGroup> {
    public static final int NEW_FACT_TYPE_GROUP_REQUEST_CODE = 400;

    public NewFactTypeGroupUiCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        super(EditFactTypeGroupActivity.class,
                NEW_FACT_TYPE_GROUP_REQUEST_CODE,
                extractor,
                intentFactory);
    }
}
