package com.revents.chronolog;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Fact {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private Date timestamp;

    @Index
    @NotNull
    private Date factDate;

    @Property
    private Integer intValue;

    @Property
    private String strValue;

    @Generated(hash = 2138191231)
    public Fact(Long id, @NotNull Date timestamp, @NotNull Date factDate,
            Integer intValue, String strValue) {
        this.id = id;
        this.timestamp = timestamp;
        this.factDate = factDate;
        this.intValue = intValue;
        this.strValue = strValue;
    }

    @Generated(hash = 381604036)
    public Fact() {
    }

    public Long getId() {
        return id;
    }

    @NotNull
    public Date getFactDate() {
        return this.factDate;
    }

    public void setFactDate(@NotNull Date factDate) {
        this.factDate = factDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStrValue() {
        return this.strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }
}
