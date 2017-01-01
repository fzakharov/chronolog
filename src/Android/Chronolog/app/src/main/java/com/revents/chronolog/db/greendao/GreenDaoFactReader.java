package com.revents.chronolog.db.greendao;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

public class GreenDaoFactReader implements FactReader
{
    @Override
    public FactType loadFactType(long id) {
        return new FactType();
    }
}
