package com.revents.chronolog.features;

import com.revents.chronolog.model.FactTypeGroup;

public class SelectFactTypeGroupUiCommand extends ResultUiCommand<FactTypeGroup> {
    public static final int FACT_TYPE_GROUP_ID_REQUEST_CODE = 300;

    public SelectFactTypeGroupUiCommand(IntentFactory intentFactory, IntentExtractor<FactTypeGroup> extractor) {
        super(FactTypeGroupsActivity.class,
                FACT_TYPE_GROUP_ID_REQUEST_CODE,
                extractor,
                intentFactory);
    }
}
