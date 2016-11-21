package com.revents.chronolog;

import java.util.Date;

public interface StorageWriter {
    long writeRecord(Date date, Date factDate, int intValue, String strValue);
}

