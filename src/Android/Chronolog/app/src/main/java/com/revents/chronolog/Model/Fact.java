package com.revents.chronolog.model;

import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

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
    private Long longValue;

    @Property
    private String strValue;

    private long factTypeId;

    @ToOne(joinProperty = "factTypeId")
    private FactType factType;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 613572807)
    private transient FactDao myDao;

    @Generated(hash = 1950071540)
    public Fact(Long id, @NotNull Date timestamp, @NotNull Date factDate,
            Long longValue, String strValue, long factTypeId) {
        this.id = id;
        this.timestamp = timestamp;
        this.factDate = factDate;
        this.longValue = longValue;
        this.strValue = strValue;
        this.factTypeId = factTypeId;
    }

    @Generated(hash = 381604036)
    public Fact() {
    }

    public Long getId() {
        return this.id;
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

    public Date getFactDate() {
        return this.factDate;
    }

    public void setFactDate(Date factDate) {
        this.factDate = factDate;
    }

    public Long getLongValue() {
        return this.longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public String getStrValue() {
        return this.strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public long getFactTypeId() {
        return this.factTypeId;
    }

    public void setFactTypeId(long factTypeId) {
        this.factTypeId = factTypeId;
    }

    @Generated(hash = 1688759559)
    private transient Long factType__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 222930702)
    public FactType getFactType() {
        long __key = this.factTypeId;
        if (factType__resolvedKey == null || !factType__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FactTypeDao targetDao = daoSession.getFactTypeDao();
            FactType factTypeNew = targetDao.load(__key);
            synchronized (this) {
                factType = factTypeNew;
                factType__resolvedKey = __key;
            }
        }
        return factType;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 259983631)
    public void setFactType(@NotNull FactType factType) {
        if (factType == null) {
            throw new DaoException(
                    "To-one property 'factTypeId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.factType = factType;
            factTypeId = factType.getId();
            factType__resolvedKey = factTypeId;
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
    @Generated(hash = 992845822)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFactDao() : null;
    }
}
