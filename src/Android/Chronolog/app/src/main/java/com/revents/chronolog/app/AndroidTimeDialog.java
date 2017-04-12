package com.revents.chronolog.app;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AndroidTimeDialog implements TimeDialog {
    private Date mSelectedDate;

    @Override
    public void show(Date selectedDate, Context context, final DateListener listener) {
        mSelectedDate = selectedDate;
        Calendar c = new GregorianCalendar();
        c.setTime(mSelectedDate);


        TimePickerDialog d = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c = new GregorianCalendar();
                c.setTime(mSelectedDate);
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);

                mSelectedDate = c.getTime();

                listener.onDateChanged(mSelectedDate);
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);

        d.show();
    }
}
