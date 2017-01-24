package com.revents.chronolog.app;

import android.graphics.Color;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

public class DefaultAppCreateListener implements AppCreateListener {

    public final static Long OTHER_GROUP_ID = 1L;
    public static final Long DEFAULT_VALUE_DESCR_ID = 1L;
    public static final Long COFFEE_FACT_TYPE_ID = 1L;

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
        FactTypeGroup group = new FactTypeGroup(OTHER_GROUP_ID, "Разное", "", Color.TRANSPARENT);
        mFactWriter.write(group);

        ValueDescriptor valueDescriptor = new ValueDescriptor(DEFAULT_VALUE_DESCR_ID, "Число", "Событие со значением типа целое число.", "default", "");
        mFactWriter.write(valueDescriptor);

        FactType ft = new FactType(COFFEE_FACT_TYPE_ID, "Кофе", "", false, group.getId(), valueDescriptor.getId());
        mFactWriter.write(ft);
    }
}
