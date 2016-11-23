package com.revents.chronolog;

public class GreenDaoFactWriter {
    private DateTimeProvider dtProv;

    public GreenDaoFactWriter(DateTimeProvider dtProv) {
        if (dtProv == null)
            throw new IllegalArgumentException("dtProv");

        this.dtProv = dtProv;
    }

    public Fact write(Fact fact){
        if (fact == null)
            throw new IllegalArgumentException("fact");

        fact.setTimestamp(dtProv.getDate());

        return fact;
    }
}
