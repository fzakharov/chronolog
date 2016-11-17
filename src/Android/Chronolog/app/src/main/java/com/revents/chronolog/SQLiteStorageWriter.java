package com.revents.chronolog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class SQLiteStorageWriter implements StorageWriter {

    public SQLiteStorageWriter() {
    }

    @Override
    public void writeRecord(Date date, Date factDate, int intValue, String strValue) {

    }
}
