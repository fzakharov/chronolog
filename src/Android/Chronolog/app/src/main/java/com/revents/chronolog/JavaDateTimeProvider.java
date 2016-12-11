package com.revents.chronolog;

import java.util.Date;

public class JavaDateTimeProvider implements DateTimeProvider {
    @Override
    public Date getDate() {
        return new Date();
    }
}
