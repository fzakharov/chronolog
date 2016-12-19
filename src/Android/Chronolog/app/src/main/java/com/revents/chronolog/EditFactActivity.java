package com.revents.chronolog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.revents.chronolog.Model.DaoSession;
import com.revents.chronolog.Model.Fact;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// TODO: 11.12.2016 date Dialog http://metanit.com/java/android/18.1.php
public class EditFactActivity extends AppCompatActivity {

    private DaoSession daoSession;
    private long mfactTypeId;
    private EditText mDateEdit;
    private SimpleDateFormat mDateFormatter;
    Calendar dateAndTime = Calendar.getInstance();
    private EditText mValueEditTxt;
    private EditText mStrValueEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fact);

        daoSession = ((App) getApplication()).getDaoSession();
        mfactTypeId = getIntent().getLongExtra("factTypeId", -1);
        mDateEdit = (EditText) findViewById(R.id.dateEdit);
        mValueEditTxt = (EditText) findViewById(R.id.valueEditTxt);
        mStrValueEditTxt = (EditText) findViewById(R.id.strValueEditTxt);

        setInitialDateTime();

        mValueEditTxt.requestFocus();
    }

    public void addFactClick(View v) {
        addFact(mfactTypeId);
        finish();
    }

    public void setDateClick(View v) {
        new DatePickerDialog(EditFactActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void setTimeClick(View v) {
        new TimePickerDialog(EditFactActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    private void setInitialDateTime() {

        mDateEdit.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE |
                        DateUtils.FORMAT_SHOW_YEAR |
                        DateUtils.FORMAT_SHOW_TIME));
    }

    private void addFact(long fatcTypeId) {
        JavaDateTimeProvider dateTimeProvider = new JavaDateTimeProvider();
        GreenDaoFactWriter wr = new GreenDaoFactWriter(dateTimeProvider, daoSession);

        long intVal = getIntVal();
        String strVal = mStrValueEditTxt.getText().toString();

        Fact fact = new Fact(null, null, dateAndTime.getTime(), intVal, strVal, fatcTypeId);
        wr.write(fact);
    }

    private int getIntVal() {
        int myNum = 0;

        try {
            myNum = Integer.parseInt(mValueEditTxt.getText().toString());
        } catch(NumberFormatException nfe) {
            return 1; // TODO: 12.12.2016 error handling to prevent invalid valies write
        }

        return myNum;
    }
}
