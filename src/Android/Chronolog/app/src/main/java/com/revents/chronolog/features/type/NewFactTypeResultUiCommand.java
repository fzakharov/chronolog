package com.revents.chronolog.features.type;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.model.FactType;

public class NewFactTypeResultUiCommand extends ResultResultUiCommand<FactType> {
    public static final int NEW_FACT_TYPE_REQUEST_CODE = 200;

    public NewFactTypeResultUiCommand(IntentFactory intentFactory, FactReader factReader) {
        super(EditFactTypeActivity.class,
                NEW_FACT_TYPE_REQUEST_CODE,
                new FactTypeIntentExtractor(factReader),
                intentFactory);
    }
}
