package com.revents.chronolog;

import com.revents.chronolog.Model.DaoSession;
import com.revents.chronolog.Model.Fact;
import com.revents.chronolog.Model.FactDao;

public class GreenDaoFactWriter {
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

    public void write(Fact fact){
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
}
