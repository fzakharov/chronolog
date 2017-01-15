package com.revents.chronolog.db;

import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

import java.util.List;

public interface FactReader {
    FactType loadFactType(long id);
    FactTypeGroup loadFactTypeGroup(long id);
    ValueDescriptor loadValueDescriptor(long id);
    List<FactType> loadFactTypes();
}
