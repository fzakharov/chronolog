package com.revents.chronolog.features;

import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

public class FactTypeIntentExtractor implements IntentExtractor<FactType>
{
    public static final String FACT_TYPE_ID_EXTRA_NAME = "FactTypeId";
    private final FactReader mFactReader;

    public FactTypeIntentExtractor(FactReader factReader){
        mFactReader = factReader;
    }

    @Override
    public FactType extract(Intent data) {
        long id = data.getLongExtra(FACT_TYPE_ID_EXTRA_NAME, -1);
        return mFactReader.loadFactType(id);
    }
}
