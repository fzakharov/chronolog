package com.revents.chronolog.features;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactTypeGroup;

public class FactTypeGroupIntentExtractor extends LoadByIdIntentExtractorBase<FactTypeGroup> {
    public static final String FACT_TYPE_GROUP_ID_EXTRA_NAME = "FactTypeGroupId";

    public FactTypeGroupIntentExtractor(FactReader factReader) {
        super(FACT_TYPE_GROUP_ID_EXTRA_NAME, factReader);
    }

    @Override
    protected FactTypeGroup loadFrom(FactReader factReader, long id) {
        return factReader.loadFactTypeGroup(id);
    }
}
