package com.revents.chronolog.features;

import android.app.Activity;
import android.content.Intent;

import com.revents.chronolog.EditFactActivity;
import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import java.util.Date;

public class EditFactActivityCommand implements ActivityCommand<Fact> {

    public static final int EDIT_FACT_REQUEST_CODE = 200;

    @Override
    public void execute(Activity current) {
        Intent intent = new Intent(current, EditFactActivity.class);
        current.startActivityForResult(intent, EDIT_FACT_REQUEST_CODE);
    }

    @Override
    public Fact onResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_FACT_REQUEST_CODE) {
            String str = data.getStringExtra("FactStrValue");

            return new Fact(42l, new Date(), new Date(), 22l, str, 22l);
        }

        return null;
    }
}
