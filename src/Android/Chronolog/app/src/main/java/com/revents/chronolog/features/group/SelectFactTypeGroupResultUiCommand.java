package com.revents.chronolog.features.group;

import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.model.FactTypeGroup;

public class SelectFactTypeGroupResultUiCommand extends ResultResultUiCommand<FactTypeGroup> {
    public static final int FACT_TYPE_GROUP_ID_REQUEST_CODE = 300;

    public SelectFactTypeGroupResultUiCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        super(FactTypeGroupsActivity.class,
                FACT_TYPE_GROUP_ID_REQUEST_CODE,
                extractor,
                intentFactory);
    }
}
