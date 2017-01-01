package com.revents.chronolog.app;

import android.app.Activity;
import android.content.Intent;

public interface ActivityCommand {
    void execute(Activity current);
    void onResult(int requestCode, int resultCode, Intent data);
}

