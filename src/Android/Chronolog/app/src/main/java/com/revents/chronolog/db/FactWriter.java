package com.revents.chronolog.db;

import com.revents.chronolog.model.Fact;

public interface FactWriter
{
    void write(Fact fact);
    void delete(Fact fact);
}
