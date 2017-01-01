package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

public class NewFactActivityCommand implements ActivityCommand<Fact> {

    private ActivityCommand<FactType> mSelectFactTypeCommand;
    private Activity mCurrent;
    private FactType mFactType;
    private EditFactActivityCommand mEditFactActivityCommand;

    public NewFactActivityCommand(ActivityCommand<FactType> selectFactTypeCommand) {

        mSelectFactTypeCommand = selectFactTypeCommand;
    }

    @Override
    public void execute(Activity current) {
        mCurrent = current;
        mSelectFactTypeCommand.execute(mCurrent);
    }

    @Override
    public Fact onResult(int requestCode, int resultCode, Intent data) {
        if (mFactType == null) {
            mFactType = mSelectFactTypeCommand.onResult(requestCode, resultCode, data);

            if (mFactType != null) {
                mEditFactActivityCommand = new EditFactActivityCommand();
                mEditFactActivityCommand.execute(mCurrent);
            }
        }
        else if (mFactType != null)
        {
            return mEditFactActivityCommand.onResult(requestCode, resultCode, data);
        }

        return null;
    }
}
