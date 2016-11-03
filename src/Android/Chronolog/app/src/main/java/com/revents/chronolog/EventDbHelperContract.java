package com.revents.chronolog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/* Inner class that defines the table contents */
abstract class EventEntry implements BaseColumns {
    public static final String TABLE_NAME = "event";
    public static final String COLUMN_NAME_TIMESTAMP = "eventTimeStamp";
    public static final String COLUMN_NAME_EVENT_TYPE_ID = "eventTypeId";
    public static final String COLUMN_NAME_EVENT_TIME = "eventTime";
    public static final String COLUMN_NAME_EVENT_INT_VALUE = "eventIntValue";
    public static final String COLUMN_NAME_EVENT_STR_VALUE = "eventStrValue";
    public static final int EVENT_TYPE_ID_WAKEUP = 100;
    public static final int EVENT_TYPE_ID_TOSLEEP = 200;
}

class EventDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "events.db";

    private static final String SQL_CREATE_EVENTS =
            "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
                    EventEntry._ID + " INTEGER PRIMARY KEY," +
                    EventEntry.COLUMN_NAME_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    EventEntry.COLUMN_NAME_EVENT_TYPE_ID + " INTEGER," +
                    EventEntry.COLUMN_NAME_EVENT_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    EventEntry.COLUMN_NAME_EVENT_INT_VALUE + " INTEGER," +
                    EventEntry.COLUMN_NAME_EVENT_STR_VALUE + " TEXT" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME;

    public EventDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EVENTS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
