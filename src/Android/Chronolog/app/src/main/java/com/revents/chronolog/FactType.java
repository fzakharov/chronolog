package com.revents.chronolog;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FactType
{
    @Id(autoincrement = true)
    private Long id;

    @Property
    @NotNull
    private String Name;

    @Property
    @NotNull
    private String Description;

    @Generated(hash = 1434017515)
    public FactType(Long id, @NotNull String Name, @NotNull String Description) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
    }

    @Generated(hash = 21486405)
    public FactType() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
