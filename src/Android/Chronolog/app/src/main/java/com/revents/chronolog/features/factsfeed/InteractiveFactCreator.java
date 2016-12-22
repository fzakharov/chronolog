package com.revents.chronolog.features.factsfeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.revents.chronolog.features.facttypes.FactTypesActivity;

public class InteractiveFactCreator implements FactCreator {


    private Context context;

    public InteractiveFactCreator(Context context)
    {
        this.context = context;
    }

    @Override
    public void addFact() {

        try {
            Intent intent = new Intent(context, FactTypesActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);
        } catch (Exception ex) {
            String msg = ex.toString();
        }
    }
}
