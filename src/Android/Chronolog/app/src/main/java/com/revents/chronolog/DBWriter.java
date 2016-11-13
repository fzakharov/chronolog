package com.revents.chronolog;

import java.util.Date;

interface DateTimeProvider {
    Date getDate();
}

class JavaDateTimeProvider implements DateTimeProvider {
    @Override
    public Date getDate() {
        return new Date();
    }
}

interface DbWriter {
    void writeRecord(Date date, Date factDate, int intValue, String strValue);
}
