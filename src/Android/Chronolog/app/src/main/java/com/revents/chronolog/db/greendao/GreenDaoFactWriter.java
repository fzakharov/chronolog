package com.revents.chronolog.db.greendao;

import com.revents.chronolog.app.DateTimeProvider;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactDao;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

public class GreenDaoFactWriter implements FactWriter {
    private DateTimeProvider mDateTimeProvider;
    private DaoSession mSession;

    public GreenDaoFactWriter(DateTimeProvider dateTimeProvider, DaoSession session) {
        if (dateTimeProvider == null)
            throw new IllegalArgumentException("dateTimeProvider");

        if (session == null)
            throw new IllegalArgumentException("session");

        mSession = session;
        mDateTimeProvider = dateTimeProvider;
    }

    public void write(Fact fact) {
        if (fact == null)
            throw new IllegalArgumentException("fact");

        fact.setTimestamp(mDateTimeProvider.getDate());

        FactDao factDao = mSession.getFactDao();
        factDao.insertOrReplace(fact);
    }

    public void delete(Fact fact) {
        if (fact == null)
            throw new IllegalArgumentException("fact");

        FactDao factDao = mSession.getFactDao();
        factDao.delete(fact);
    }

    @Override
    public void write(ValueDescriptor valueDescriptor) {

        // TODO: 07.01.2017 test
        mSession.getValueDescriptorDao().insertOrReplace(valueDescriptor);
    }

    @Override
    public void write(FactTypeGroup group) {
        // TODO: 07.01.2017 test
        mSession.getFactTypeGroupDao().insertOrReplace(group);
    }

    @Override
    public void write(FactType factType) {
        // TODO: 07.01.2017 test
        mSession.getFactTypeDao().insertOrReplace(factType);
    }
}
