package com.revents.chronolog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ReloadEvents();
    }

    public void onWakeupEventBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_WAKEUP);
    }

    public void onToSleepEventBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_TOSLEEP);
    }

    public void onBreakfast1StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_BREAKFASTSTAR, 1);
    }

    public void onBreakfast2StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_BREAKFASTSTAR, 2);
    }

    public void onBreakfast3StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_BREAKFASTSTAR, 3);
    }

    public void onBreakfast4StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_BREAKFASTSTAR, 4);
    }

    public void onBreakfast5StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_BREAKFASTSTAR, 5);
    }

    public void onLunch1StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_LUNCHSTAR, 1);
    }

    public void onLunch2StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_LUNCHSTAR, 2);
    }

    public void onLunch3StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_LUNCHSTAR, 3);
    }

    public void onLunch4StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_LUNCHSTAR, 4);
    }

    public void onLunch5StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_LUNCHSTAR, 5);
    }

    public void onDinner1StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 1);
    }

    public void onDinner2StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 2);
    }

    public void onDinner3StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 3);
    }

    public void onDinner4StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 4);
    }

    public void onDinner5StarBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_DINNERSTAR, 5);
    }

    public void onCoffeeEventBtnClick(View v) {
        WriteEvent(EventEntry.EVENT_TYPE_ID_COFFEE, 1);
    }

    private void WriteEvent(int eventTypeId) {
        WriteEvent(eventTypeId, null);
    }

    private void WriteEvent(int eventTypeId, Integer intValue) {
        EventDbHelper mDbHelper = new EventDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_NAME_EVENT_TYPE_ID, eventTypeId);

        if (intValue != null)
            values.put(EventEntry.COLUMN_NAME_EVENT_INT_VALUE, intValue.intValue());

        long newRowId;
        newRowId = db.insert(EventEntry.TABLE_NAME, null, values);

        mDbHelper.close();

        DebugRecord(newRowId);
        ReloadEvents();
    }

    private void ReloadEvents() {

        EventDbHelper mDbHelper = new EventDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("select * from " + EventEntry.TABLE_NAME, null);

        EventRecordDto[] data = new EventRecordDto[c.getCount()];

        for (int i = 0; i < c.getCount(); i++) {
            c.moveToNext();

            data[i]= new EventRecordDto(c);
        }

        EventListViewItemAdapter adapter =
                new EventListViewItemAdapter(this, data);

        c.close();

        mDbHelper.close();

        ListView lvEvents = (ListView) findViewById(R.id.lvEvents);
        lvEvents.setAdapter(adapter);
    }

    private void DebugRecord(long recordId) {
        TextView txt = (TextView) findViewById(R.id.debugTxt);
        txt.setText("");

        EventDbHelper mDbHelper = new EventDbHelper(this);
        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            Cursor c = db.rawQuery("select * from " + EventEntry.TABLE_NAME + " where " + EventEntry._ID + "='" + recordId + "'", null);


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
            }

            txt.setText(text);

            c.close();
        } catch (Exception ex) {
            txt.setText(ex.toString());
        }
        mDbHelper.close();
    }
}
