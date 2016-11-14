package com.revents.chronolog;

public class FactWriter {
    private StorageWriter _storageWriter;
    private DateTimeProvider _dtProv;

    public FactWriter(StorageWriter storageWriter, DateTimeProvider dtProv) {
        if (storageWriter == null)
            throw new IllegalArgumentException("storageWriter");

        if (dtProv == null)
            throw new IllegalArgumentException("dtProv");

        _storageWriter = storageWriter;
        _dtProv = dtProv;
    }

    public void write(Fact fact){
        if (fact == null)
            throw new IllegalArgumentException("fact");

        _storageWriter.writeRecord(_dtProv.getDate(), fact.factDate, fact.intValue, fact.strValue);
    }
}
