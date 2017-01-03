package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

import static android.app.Activity.RESULT_OK;

public class NewFactTypeActivityCommand implements ActivityCommand<FactType> {
    public static final int NEW_FACT_TYPE_REQUEST_CODE = 200;
    private IntentFactory mIntentFactory;
    private FactReader mFactReader;

    public NewFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        mIntentFactory = intentFactory;
        mFactReader = factReader;
    }

    @Override
    public void execute(Activity activity) {
        Intent data = mIntentFactory.Create(activity, EditFactTypeActivity.class);
        activity.startActivityForResult(data, NEW_FACT_TYPE_REQUEST_CODE);
    }

    @Override
    public FactType onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return null;

        if (requestCode != NEW_FACT_TYPE_REQUEST_CODE)
            return null;

        long ftId = data.getLongExtra(EditFactTypeActivity.FACT_TYPE_ID_EXTRA_NAME, -1);

        if (ftId <= 0)
            return null;

        return mFactReader.loadFactType(ftId);
    }
}
