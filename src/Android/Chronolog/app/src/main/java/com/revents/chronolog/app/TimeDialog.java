package com.revents.chronolog.app;

import java.util.Date;

public interface TimeDialog {
    Date getSelectedTime();

    boolean Show(Date date);
}
