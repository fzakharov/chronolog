package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.app.ParametrizedActivityCommand;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

public class NewFactResultUiCommand implements ResultUiCommand<Fact> {

    private ResultUiCommand<FactType> mSelectFactTypeCommand;
    private ParametrizedActivityCommand<Fact, FactType> mEditFactCommand;

    public NewFactResultUiCommand(ResultUiCommand<FactType> selectFactTypeCommand, ParametrizedActivityCommand<Fact, FactType> editFactCommand) {

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