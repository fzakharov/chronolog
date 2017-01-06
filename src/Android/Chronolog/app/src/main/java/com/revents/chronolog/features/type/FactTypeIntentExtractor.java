package com.revents.chronolog.features.type;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.features.LoadByIdIntentExtractorBase;
import com.revents.chronolog.model.FactType;

public class FactTypeIntentExtractor extends LoadByIdIntentExtractorBase<FactType> {
    public static final String FACT_TYPE_ID_EXTRA_NAME = "FactTypeId";

    public FactTypeIntentExtractor(FactReader factReader) {
        super(FACT_TYPE_ID_EXTRA_NAME, factReader);
    }

    @Override
    protected FactType loadFrom(FactReader factReader, long id) {
        return factReader.loadFactType(id);
    }
}

