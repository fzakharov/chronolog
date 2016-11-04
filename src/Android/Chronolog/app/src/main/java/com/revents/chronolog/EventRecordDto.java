package com.revents.chronolog;

import android.database.Cursor;

public class EventRecordDto
{
    public final long id;
    public final long eventTime;
    public final long eventTypeId;
    public final long eventIntValue;
    public final long eventStrValue;
    public final long timeStamp;

    public EventRecordDto(Cursor c)
    {
        id = c.getLong(c.getColumnIndex(EventEntry._ID));
        eventTime = c.getLong(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_TIME));
        timeStamp = c.getLong(c.getColumnIndex(EventEntry.COLUMN_NAME_TIMESTAMP));
        eventTypeId = c.getLong(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_TYPE_ID));
        eventIntValue = c.getLong(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_INT_VALUE));
        eventStrValue = c.getLong(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_STR_VALUE));
    }
}
