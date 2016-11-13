package com.revents.chronolog;

import java.util.Date;

public class Fact {
    private final Date factDate;
    private final int intValue;
    private final String strValue;

    public Fact(Date factDate, int intValue, String strValue) {
        this.factDate = factDate;
        this.intValue = intValue;
        this.strValue = strValue;
    }


}
