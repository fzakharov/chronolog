package com.revents.chronolog.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ValueDescriptor
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
    private String className;

    @Property
    @NotNull
    private String settings;

    @Generated(hash = 1646934497)
    public ValueDescriptor(Long id, @NotNull String name,
            @NotNull String description, @NotNull String className,
            @NotNull String settings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.className = className;
        this.settings = settings;
    }

    @Generated(hash = 440690635)
    public ValueDescriptor() {
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

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSettings() {
        return this.settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }
}
