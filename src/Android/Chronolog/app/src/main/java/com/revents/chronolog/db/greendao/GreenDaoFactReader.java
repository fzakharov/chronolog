package com.revents.chronolog.db.greendao;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.FactType;

public class GreenDaoFactReader implements FactReader {
    private DaoSession mSession;

    public GreenDaoFactReader(DaoSession session) {

        mSession = session;
    }

    @Override
    public FactType loadFactType(long id) {

        return mSession.getFactTypeDao().load(id);
    }
}
