package com.revents.chronolog.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity
public class FactType
{
    @Id(autoincrement = true)
    private Long id;

    @Property
    @NotNull
    private String name;

    @Property
    @NotNull
    private String description;

    private long factTypeGroupId;
    @ToOne(joinProperty = "factTypeGroupId")
    private FactTypeGroup factTypeGroup;

    private long valueDescriptorId;
    @ToOne(joinProperty = "valueDescriptorId")
    private ValueDescriptor valueDescriptor;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 980237178)
    private transient FactTypeDao myDao;
    @Generated(hash = 2042931279)
    public FactType(Long id, @NotNull String name, @NotNull String description,
            long factTypeGroupId, long valueDescriptorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.factTypeGroupId = factTypeGroupId;
        this.valueDescriptorId = valueDescriptorId;
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
    public long getFactTypeGroupId() {
        return this.factTypeGroupId;
    }
    public void setFactTypeGroupId(long factTypeGroupId) {
        this.factTypeGroupId = factTypeGroupId;
    }
    public long getValueDescriptorId() {
        return this.valueDescriptorId;
    }
    public void setValueDescriptorId(long valueDescriptorId) {
        this.valueDescriptorId = valueDescriptorId;
    }
    @Generated(hash = 615917188)
    private transient Long factTypeGroup__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2103438985)
    public FactTypeGroup getFactTypeGroup() {
        long __key = this.factTypeGroupId;
        if (factTypeGroup__resolvedKey == null
                || !factTypeGroup__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FactTypeGroupDao targetDao = daoSession.getFactTypeGroupDao();
            FactTypeGroup factTypeGroupNew = targetDao.load(__key);
            synchronized (this) {
                factTypeGroup = factTypeGroupNew;
                factTypeGroup__resolvedKey = __key;
            }
        }
        return factTypeGroup;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 456736360)
    public void setFactTypeGroup(@NotNull FactTypeGroup factTypeGroup) {
        if (factTypeGroup == null) {
            throw new DaoException(
                    "To-one property 'factTypeGroupId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.factTypeGroup = factTypeGroup;
            factTypeGroupId = factTypeGroup.getId();
            factTypeGroup__resolvedKey = factTypeGroupId;
        }
    }
    @Generated(hash = 968424378)
    private transient Long valueDescriptor__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1429221557)
    public ValueDescriptor getValueDescriptor() {
        long __key = this.valueDescriptorId;
        if (valueDescriptor__resolvedKey == null
                || !valueDescriptor__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ValueDescriptorDao targetDao = daoSession.getValueDescriptorDao();
            ValueDescriptor valueDescriptorNew = targetDao.load(__key);
            synchronized (this) {
                valueDescriptor = valueDescriptorNew;
                valueDescriptor__resolvedKey = __key;
            }
        }
        return valueDescriptor;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 986190609)
    public void setValueDescriptor(@NotNull ValueDescriptor valueDescriptor) {
        if (valueDescriptor == null) {
            throw new DaoException(
                    "To-one property 'valueDescriptorId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.valueDescriptor = valueDescriptor;
            valueDescriptorId = valueDescriptor.getId();
            valueDescriptor__resolvedKey = valueDescriptorId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 122300598)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFactTypeDao() : null;
    }
}

