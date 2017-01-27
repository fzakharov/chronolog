package com.revents.chronolog.app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AndroidDateDialog implements DateDialog {
    private Date mSelectedDate;
    private boolean mResult = false;

    @Override
    public void show(Date selectedDate, Context context, final DateListener listener) {
        mSelectedDate = selectedDate;
        Calendar c = new GregorianCalendar();
        c.setTime(mSelectedDate);


        DatePickerDialog d = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c = new GregorianCalendar();
                c.setTime(mSelectedDate);
                c.set(year, month, dayOfMonth);

                mSelectedDate = c.getTime();

                listener.onDateChanged(mSelectedDate);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        d.show();
    }
}
