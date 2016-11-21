package com.revents.chronolog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class SQLiteStorageWriter implements StorageWriter {

    public SQLiteStorageWriter() {
    }

    @Override
    public long writeRecord(Date date, Date factDate, int intValue, String strValue) {
        return -1;
    }
}
