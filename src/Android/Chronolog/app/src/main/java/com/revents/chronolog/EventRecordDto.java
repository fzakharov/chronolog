package com.revents.chronolog;

import android.database.Cursor;

public class EventRecordDto
{
    public final long id;
    public final long eventTime;
    public String eventTimeStr;
    public final int eventTypeId;
    public final int eventIntValue;
    public final String eventStrValue;
    public final long timeStamp;

    public EventRecordDto(Cursor c)
    {
        id = c.getLong(c.getColumnIndex(EventEntry._ID));
        eventTime = c.getInt(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_TIME));
        eventTimeStr = c.getString(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_TIME));
        timeStamp = c.getLong(c.getColumnIndex(EventEntry.COLUMN_NAME_TIMESTAMP));
        eventTypeId = c.getInt(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_TYPE_ID));
        eventIntValue = c.getInt(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_INT_VALUE));
        eventStrValue = c.getString(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_STR_VALUE));
    }
}
