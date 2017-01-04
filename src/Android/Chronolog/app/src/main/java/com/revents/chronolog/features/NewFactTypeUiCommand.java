package com.revents.chronolog.features;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

public class NewFactTypeUiCommand extends ResultUiCommand<FactType> {
    public static final int NEW_FACT_TYPE_REQUEST_CODE = 200;

    public NewFactTypeUiCommand(IntentFactory intentFactory, FactReader factReader) {
        super(EditFactTypeActivity.class,
                NEW_FACT_TYPE_REQUEST_CODE,
                new FactTypeIntentExtractor(factReader),
                intentFactory);
    }
}
