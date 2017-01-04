package com.revents.chronolog.features;

import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactTypeGroup;

public class FactTypeGroupIntentExtractor implements IntentExtractor<FactTypeGroup>{
    public static final String FACT_TYPE_GROUP_ID_EXTRA_NAME = "FactTypeGroupId";

    private final FactReader mFactReader;

    public FactTypeGroupIntentExtractor(FactReader factReader){
        mFactReader = factReader;
    }

    @Override
    public FactTypeGroup extract(Intent data) {
        long id = data.getLongExtra(FACT_TYPE_GROUP_ID_EXTRA_NAME, -1);
        return mFactReader.loadFactTypeGroup(id);
    }
}
