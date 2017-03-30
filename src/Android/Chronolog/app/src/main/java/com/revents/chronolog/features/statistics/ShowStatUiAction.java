package com.revents.chronolog.features.statistics;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.statistics.FactTypeStatisticsActivity;
import com.revents.chronolog.features.type.FactTypeIntentExtractor;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.ui.UiAction;

public class ShowStatUiAction implements UiAction<Fact> {

    private IntentFactory mIntentFactory;

    public ShowStatUiAction(IntentFactory intentFactory) {

        mIntentFactory = intentFactory;
    }

    @Override
    public void execute(Activity activity, Fact fact) {
        Intent i = mIntentFactory.Create(activity, FactTypeStatisticsActivity.class);
        i.putExtra(FactTypeIntentExtractor.FACT_TYPE_ID_EXTRA_NAME, fact.getFactTypeId());

        activity.startActivity(i);
    }
}
