package com.revents.chronolog.db;

import com.revents.chronolog.model.FactType;

public interface FactReader {
    FactType loadFactType(long id);
}
