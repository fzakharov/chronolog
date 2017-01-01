package com.revents.chronolog.app;

import android.app.Activity;
import android.content.Intent;

public interface ActivityCommand<T> {
    void execute(Activity current);
    T onResult(int requestCode, int resultCode, Intent data);
}

