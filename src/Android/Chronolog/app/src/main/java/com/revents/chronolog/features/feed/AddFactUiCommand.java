package com.revents.chronolog.features.feed;

import android.app.Activity;

import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.ResultResultUiCommand;
import com.revents.chronolog.features.type.FactTypeIntentExtractor;
import com.revents.chronolog.features.type.FactTypesActivity;
import com.revents.chronolog.model.FactType;

public class AddFactUiCommand extends ResultResultUiCommand<FactType> {
    public static final int FACT_TYPE_ID_REQUEST_CODE = 100;

    public AddFactUiCommand(IntentFactory intentFactory, FactReader factReader) {
        super(FactTypesActivity.class,
                FACT_TYPE_ID_REQUEST_CODE,
                new FactTypeIntentExtractor(factReader),
                intentFactory);
    }
}
