package com.revents.chronolog.app;

import android.graphics.Color;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

public class DefaultAppCreateListener implements AppCreateListener {

    private FactWriter mFactWriter;
    private FactReader mFactReader;

    public DefaultAppCreateListener(FactReader factReader, FactWriter factWriter) {
        mFactWriter = factWriter;
        mFactReader = factReader;
    }

    @Override
    public void onCreate() {
        deployAlways();
    }

    private void deployAlways() {
//        FactTypeGroup group = new FactTypeGroup(null, "Разное", "", Color.TRANSPARENT);
//
//        ValueDescriptor valueDescriptor = new ValueDescriptor(null, "Число", "Событие со значением типа целое число.", "default", "");
//
//        FactType ft = new FactType(null, "Кофе", "", false, group.getId(), valueDescriptor.getId());
    }
}
