package com.revents.chronolog;

public class GreenDaoFactWriter {
    private DateTimeProvider dtProv;
    private DaoSession session;

    public GreenDaoFactWriter(DateTimeProvider dtProv, DaoSession session) {
        if (dtProv == null)
            throw new IllegalArgumentException("dtProv");

        if (session == null)
            throw new IllegalArgumentException("session");


        this.session = session;
        this.dtProv = dtProv;
    }

    public void write(Fact fact){
        if (fact == null)
            throw new IllegalArgumentException("fact");

        fact.setTimestamp(dtProv.getDate());

        FactDao factDao = session.getFactDao();
        factDao.insert(fact);
    }
}
