package com.revents.chronolog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void onWakeupEventBtnClick(View v)
    {
        WriteEvent(EventEntry.EVENT_TYPE_ID_WAKEUP);
    }

    public void onToSleepEventBtnClick(View v)
    {
        WriteEvent(EventEntry.EVENT_TYPE_ID_TOSLEEP);
    }

    public void onDinner1StarBtnClick(View v)
    {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 1);
    }

    public void onDinner2StarBtnClick(View v)
    {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 2);
    }

    public void onDinner3StarBtnClick(View v)
    {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 3);
    }

    public void onDinner4StarBtnClick(View v)
    {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 4);
    }

    public void onDinner5StarBtnClick(View v)
    {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 5);
    }

    private void WriteEvent(int eventTypeId)
    {
        WriteEvent(eventTypeId, null);
    }

    private void WriteEvent(int eventTypeId, Integer intValue)
    {
        EventDbHelper mDbHelper = new EventDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_NAME_EVENT_TYPE_ID, eventTypeId);

        if (intValue != null)
            values.put(EventEntry.COLUMN_NAME_EVENT_INT_VALUE, intValue.intValue());

        long newRowId;
        newRowId = db.insert(EventEntry.TABLE_NAME, null, values);

        mDbHelper.close();

        DebugRecord();
    }

    private void DebugRecord()
    {
        TextView txt = (TextView) findViewById(R.id.debugTxt);
        EventDbHelper mDbHelper = new EventDbHelper(this);
        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            String[] projection = {
                    EventEntry._ID,
                    EventEntry.COLUMN_NAME_TIMESTAMP,
                    EventEntry.COLUMN_NAME_EVENT_TYPE_ID,
                    EventEntry.COLUMN_NAME_EVENT_TIME,
                    EventEntry.COLUMN_NAME_EVENT_INT_VALUE,
                    EventEntry.COLUMN_NAME_EVENT_STR_VALUE};

            String sortOrder = EventEntry._ID + " DESC";

            Cursor c = db.query(
                    EventEntry.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            txt.setText("");
            CharSequence text = txt.getText();

            for (int i = 0; i < c.getCount(); i++) {
                c.moveToNext();

                text = text + c.getString(c.getColumnIndex(EventEntry._ID));
                text = text + "|";
                text = text + c.getString(c.getColumnIndex(EventEntry.COLUMN_NAME_TIMESTAMP));
                text = text + "|";
                text = text + c.getString(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_TYPE_ID));
                text = text + "|";
                text = text + c.getString(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_TIME));
                text = text + "|";
                text = text + c.getString(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_INT_VALUE));
                text = text + "|";
                text = text + c.getString(c.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_STR_VALUE));
                text = text + "|";

                text = text + "\n";

            }

            txt.setText(text);

            c.close();
        }
        catch (Exception ex)
        {
            txt.setText(ex.toString());
        }
        mDbHelper.close();
    }
}
