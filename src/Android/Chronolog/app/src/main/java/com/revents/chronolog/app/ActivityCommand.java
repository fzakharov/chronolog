package com.revents.chronolog.app;

import android.app.Activity;

public interface ActivityCommand {
    void execute(Activity current);
}

