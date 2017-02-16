package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.features.IntentFactory;
import com.revents.chronolog.features.statistics.FactTypeStatisticsActivity;

public class ShowStatUiCommand implements UiCommand {

    private IntentFactory mIntentFactory;

    public ShowStatUiCommand(IntentFactory intentFactory){

        mIntentFactory = intentFactory;
    }

    @Override
    public void execute(Activity activity) {
        Intent i = mIntentFactory.Create(activity, FactTypeStatisticsActivity.class);
        activity.startActivity(i);
    }
}
