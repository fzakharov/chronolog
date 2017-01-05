package com.revents.chronolog.features;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.ValueDescriptor;

public class ValueDescriptorIntentExtractor extends LoadByIdIntentExtractorBase<ValueDescriptor> {
    public static final String VALUE_DESCRIPTOR_ID_EXTRA_NAME = "ValueDescriptorId";

    public ValueDescriptorIntentExtractor(FactReader factReader) {
        super(VALUE_DESCRIPTOR_ID_EXTRA_NAME, factReader);
    }

    @Override
    protected ValueDescriptor loadFrom(FactReader factReader, long id) {
        return factReader.loadValueDescriptor(id);
    }
}
