package com.revents.chronolog.app;

import com.revents.chronolog.app.DateTimeProvider;

import java.util.Date;

public class JavaDateTimeProvider implements DateTimeProvider {
    @Override
    public Date getDate() {
        return new Date();
    }
}
