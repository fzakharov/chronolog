package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.model.FactType;

public class SelectFactTypeActivityCommand implements ActivityCommand<FactType> {

    private IntentFactory mIntentFactory;
    public static final int FACT_TYPE_ID_REQUEST_CODE = 100;

    public SelectFactTypeActivityCommand(IntentFactory intentFactory) {
        mIntentFactory = intentFactory;
    }

    @Override
    public void execute(Activity current) {
        Intent intent = mIntentFactory.Create(current, FactTypesActivity.class);
        current.startActivityForResult(intent, FACT_TYPE_ID_REQUEST_CODE);
    }

    @Override
    public FactType onResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FACT_TYPE_ID_REQUEST_CODE) {
            long id = data.getLongExtra("FactTypeId", -1);

            if (id >= 0)
                return new FactType(id, "Fact type yhaa!", "", false, 0, 0);
        }

        return null;
    }
}
