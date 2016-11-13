package com.revents.chronolog;

import java.util.Date;

public class FactWriter {
    private DbWriter dbWriter;
    private DateTimeProvider dtProv;

    public FactWriter(DbWriter dbWriter, DateTimeProvider dtProv) {
        this.dbWriter = dbWriter;
        this.dtProv = dtProv;
    }

    public void write(Fact f){
        dbWriter.writeRecord(dtProv.getDate(), f.factDate, f.intValue, f.strValue);
    }
}
