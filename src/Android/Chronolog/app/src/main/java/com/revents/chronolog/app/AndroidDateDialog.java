package com.revents.chronolog.app;

import java.util.Date;

public class AndroidDateDialog implements DateDialog {
    @Override
    public Date getSelectedDate() {
        return null;
    }

    @Override
    public boolean Show(Date date) {
        return false;
    }
}
