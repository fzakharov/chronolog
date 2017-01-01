package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.Fact;

public class NewFactActivityCommand implements ActivityCommand {

    private ActivityCommand mSelectFactTypeCommand;

    public NewFactActivityCommand(ActivityCommand selectFactTypeCommand) {

        mSelectFactTypeCommand = selectFactTypeCommand;
    }

    @Override
    public void execute(Activity current) {
        mSelectFactTypeCommand.execute(current);
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {
        throw new UnsupportedOperationException();
    }
}
