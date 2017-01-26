package com.revents.chronolog.app;

import java.util.Date;

public interface DateDialog {
    Date getSelectedDate();

    boolean Show(Date date);
}

