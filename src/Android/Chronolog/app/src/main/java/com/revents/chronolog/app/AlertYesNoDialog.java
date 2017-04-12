package com.revents.chronolog.app;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Pair;

public class AlertYesNoDialog implements YesNoDialog {
    public <T> void show(Context context, final T data, String msg, final EventListener<EventArgs<Pair<Boolean, T>>> listener) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        listener.onEvent(new EventArgs<>(new Pair<>(true, data)));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        listener.onEvent(new EventArgs<>(new Pair<>(false, data)));
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msg)
                .setPositiveButton("Да", dialogClickListener)
                .setNegativeButton("Нет", dialogClickListener)
                .show();
    }
}
