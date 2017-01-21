package com.revents.chronolog.db;

import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

public interface FactWriter
{
    void write(Fact fact);
    void delete(Fact fact);

    void write(ValueDescriptor valueDescriptor);

    void write(FactTypeGroup group);

    void write(FactType capture);
}
