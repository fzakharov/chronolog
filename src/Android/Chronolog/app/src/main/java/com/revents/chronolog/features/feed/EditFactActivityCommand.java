package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.EditFactActivity;
import com.revents.chronolog.app.ParametrizedActivityCommand;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import java.util.Date;

public class EditFactActivityCommand implements ParametrizedActivityCommand<Fact, FactType> {

    @Override
    public void execute(Activity current, FactType factType) {
        throw new UnsupportedOperationException("Implement");
    }

    @Override
    public Fact onResult(Activity activity, int requestCode, int resultCode, Intent data) {
        throw new UnsupportedOperationException("Implement");
    }
}
