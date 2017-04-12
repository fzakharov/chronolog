package com.revents.chronolog.app;

import android.graphics.Color;

import com.revents.chronolog.db.DbData;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

public class DefaultAppCreateListener implements AppCreateListener {

    private FactWriter mFactWriter;

    public DefaultAppCreateListener(FactWriter factWriter) {
        mFactWriter = factWriter;
    }

    @Override
    public void onCreate() {
        deployAlways();
    }

    private void deployAlways() {

        for (ValueDescriptor v : DbData.Values.ITEMS) {
            mFactWriter.write(v);
        }

        for (FactTypeGroup g : DbData.Groups.ITEMS) {
            mFactWriter.write(g);
        }

        for (FactType f : DbData.Types.ITEMS) {
            mFactWriter.write(f);
        }
    }
}
