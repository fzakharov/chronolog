package com.revents.chronolog;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// TODO: 11.12.2016 date Dialog http://metanit.com/java/android/18.1.php
public class EditFactActivity extends AppCompatActivity {

    private DaoSession daoSession;
    private long mfactTypeId;
    private EditText mDateEdit;
    private SimpleDateFormat mDateFormatter;
    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fact);

        daoSession = ((App) getApplication()).getDaoSession();
        mfactTypeId = getIntent().getLongExtra("factTypeId", -1);

        Button closeBtn = (Button) findViewById(R.id.cancelBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ROOT);

        mDateEdit = (EditText) findViewById(R.id.dateEdit);
        mDateEdit.setText(new StringBuilder().append(mDateFormatter.format(new Date())));
    }

    public void addFactClick(View v) {
        //String name = getFromText(R.id.nameTxt);
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

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    private void setInitialDateTime() {

        mDateEdit.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    private void addFact(long fatcTypeId) {
        JavaDateTimeProvider dateTimeProvider = new JavaDateTimeProvider();
        GreenDaoFactWriter wr = new GreenDaoFactWriter(dateTimeProvider, daoSession);

        Fact fact = new Fact(null, null, dateTimeProvider.getDate(), 1, "", fatcTypeId);
        wr.write(fact);
    }
}
