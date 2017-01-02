package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.app.ParametrizedActivityCommand;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

public class NewFactActivityCommand implements ActivityCommand<Fact> {

    private ActivityCommand<FactType> mSelectFactTypeCommand;
    private ParametrizedActivityCommand<Fact, FactType> mEditFactCommand;

    public NewFactActivityCommand(ActivityCommand<FactType> selectFactTypeCommand, ParametrizedActivityCommand<Fact, FactType> editFactCommand) {

        mSelectFactTypeCommand = selectFactTypeCommand;
        mEditFactCommand = editFactCommand;
    }

    @Override
    public void execute(Activity activity) {
        mSelectFactTypeCommand.execute(activity);
    }

    @Override
    public Fact onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        FactType factType = mSelectFactTypeCommand.onResult(activity, requestCode, resultCode, data);

        if (factType != null) {
            mEditFactCommand.execute(activity, factType);
            return null;
        }

        return mEditFactCommand.onResult(activity, requestCode, resultCode, data);
    }
}
