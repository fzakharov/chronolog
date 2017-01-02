package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.FactType;

import static android.app.Activity.RESULT_OK;

public class SelectFactTypeActivityCommand implements ActivityCommand<FactType> {
    public static final int FACT_TYPE_ID_REQUEST_CODE = 100;
    public static final String FACT_TYPE_ID_EXTRA_NAME = "SelectedFactTypeId";
    private IntentFactory mIntentFactory;
    private FactReader mFactReader;

    public SelectFactTypeActivityCommand(IntentFactory intentFactory, FactReader factReader) {
        mIntentFactory = intentFactory;
        mFactReader = factReader;
    }

    @Override
    public void execute(Activity current) {
        Intent intent = mIntentFactory.Create(current, FactTypesActivity.class);
        current.startActivityForResult(intent, FACT_TYPE_ID_REQUEST_CODE);
    }

    @Override
    public FactType onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return null;

        if (requestCode != FACT_TYPE_ID_REQUEST_CODE)
            return null;

        long id = data.getLongExtra(FACT_TYPE_ID_EXTRA_NAME, -1);

        if (id >= 0)
            return mFactReader.loadFactType(id);


        return null;
    }
}
