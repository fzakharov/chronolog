package com.revents.chronolog;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FactDescriptor
{
    @Id(autoincrement = true)
    private long id;

    @Property
    @NotNull
    private String Name;

    @Property
    @NotNull
    private String Description;

    @Generated(hash = 438224640)
    public FactDescriptor(long id, @NotNull String Name,
            @NotNull String Description) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
    }

    @Generated(hash = 1013034916)
    public FactDescriptor() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
