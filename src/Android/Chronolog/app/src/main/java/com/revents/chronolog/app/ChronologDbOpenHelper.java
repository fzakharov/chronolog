package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.model.DaoMaster;

public class ChronologDbOpenHelper extends DaoMaster.OpenHelper {

    public ChronologDbOpenHelper(Context context, String name) {
        super(context, name);
    }
}
