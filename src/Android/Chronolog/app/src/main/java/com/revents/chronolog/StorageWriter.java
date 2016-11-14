package com.revents.chronolog;

import java.util.Date;

public interface StorageWriter {
    void writeRecord(Date date, Date factDate, int intValue, String strValue);
}

