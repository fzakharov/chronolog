package com.revents.chronolog.features.type;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.model.FactType;

public class SelectFactTypeResultUiCommand extends ResultResultUiCommand<FactType> {
    public static final int FACT_TYPE_ID_REQUEST_CODE = 100;

    public SelectFactTypeResultUiCommand(IntentFactory intentFactory, FactReader factReader) {
        super(FactTypesActivity.class,
                FACT_TYPE_ID_REQUEST_CODE,
                new FactTypeIntentExtractor(factReader),
                intentFactory);
    }
}