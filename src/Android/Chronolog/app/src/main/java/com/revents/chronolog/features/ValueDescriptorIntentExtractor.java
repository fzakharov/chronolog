package com.revents.chronolog.features;

import android.content.Intent;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.ValueDescriptor;

public class ValueDescriptorIntentExtractor implements IntentExtractor<ValueDescriptor>
{
    public static final String VALUE_DESCRIPTOR_ID_EXTRA_NAME = "ValueDescriptorId";
    private final FactReader mFactReader;

    public ValueDescriptorIntentExtractor(FactReader factReader){
        mFactReader = factReader;
    }

    @Override
    public ValueDescriptor extract(Intent data) {
        long id = data.getLongExtra(VALUE_DESCRIPTOR_ID_EXTRA_NAME, -1);
        return mFactReader.loadValueDescriptor(id);
    }
}
