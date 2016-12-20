package com.revents.chronolog.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FactTypeGroup
{
    @Id(autoincrement = true)
    private Long id;

    @Property
    @NotNull
    private String name;

    @Property
    @NotNull
    private String description;

    @Property
    @NotNull
    private String color;

    @Generated(hash = 47500634)
    public FactTypeGroup(Long id, @NotNull String name, @NotNull String description,
            @NotNull String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
    }

    @Generated(hash = 342683938)
    public FactTypeGroup() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
