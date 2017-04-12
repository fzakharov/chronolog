package com.revents.chronolog.features.value;

import com.revents.chronolog.features.value.ValueType;
import com.revents.chronolog.features.value.ValueTypesProvider;

public class HardCodedValueTypesProvider implements ValueTypesProvider {

    @Override
    public ValueType[] getValueTypes() {
        return new ValueType[]{
                new ValueType("className", "Событие", "Простое событие.")
        };
    }
}
