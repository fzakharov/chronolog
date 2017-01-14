package com.revents.chronolog.db.greendao;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

// TODO: 04.01.2017 Tests
public class GreenDaoFactReader implements FactReader {
    private DaoSession mSession;

    public GreenDaoFactReader(DaoSession session) {

        mSession = session;
    }

    @Override
    public FactType loadFactType(long id) {

        return mSession.getFactTypeDao().load(id);
    }

    @Override
    public FactTypeGroup loadFactTypeGroup(long id) {
        return mSession.getFactTypeGroupDao().load(id);
    }

    @Override
    public ValueDescriptor loadValueDescriptor(long id) {
        return mSession.getValueDescriptorDao().load(id);
    }
}