package com.revents.chronolog.app;

import java.util.Date;

public class AndroidTimeDialog implements TimeDialog {
    @Override
    public Date getSelectedTime() {
        return null;
    }

    @Override
    public boolean Show(Date date) {
        return false;
    }
}
