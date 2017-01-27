package com.revents.chronolog.app;

import java.util.Date;

public interface DateTimeProvider {
    Date getDate();

    String toDateString(Date date);

    String toTimeString(Date newDate);
}
