package com.revents.chronolog.db.greendao;

import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactDao;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeDao;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

import org.greenrobot.greendao.query.Query;

import java.util.Date;
import java.util.List;

// TODO: 04.01.2017 Tests
public class GreenDaoFactReader implements FactReader {
    public static final long FACTSFEED_PERIOD = 30L * 24L * 60L * 60L * 1000L;
    private DaoSession mSession;

    public GreenDaoFactReader(DaoSession session) {

        mSession = session;
    }

    @Override
    public FactType loadFactType(long id) {
        return mSession.getFactTypeDao().load(id);
    }

    @Override
    public FactTypeGroup loadFactTypeGroup(long id) {
        return mSession.getFactTypeGroupDao().load(id);
    }

    @Override
    public ValueDescriptor loadValueDescriptor(long id) {
        return mSession.getValueDescriptorDao().load(id);
    }

    @Override
    public List<FactType> loadFactTypes() {
        Query<FactType> query = mSession.getFactTypeDao()
                .queryBuilder()
                .orderAsc(FactTypeDao.Properties.FactTypeGroupId, FactTypeDao.Properties.Id)
                .build();

        return query.list();
    }

    @Override
    public List<Fact> loadFactsfeed() {
        Date fromDate = new Date(new Date().getTime() - FACTSFEED_PERIOD);

        FactDao fd = mSession.getFactDao();
        Query<Fact> factsQuery = fd.queryBuilder()
                .where(FactDao.Properties.FactDate.ge(fromDate.getTime()))
                .orderDesc(FactDao.Properties.FactDate)
                .build();

        return factsQuery.list();
    }
}
