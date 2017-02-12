package com.revents.chronolog.db.greendao;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.model.DaoMaster;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeDao;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.FactTypeGroupDao;
import com.revents.chronolog.model.ValueDescriptor;
import com.revents.chronolog.model.ValueDescriptorDao;

import org.greenrobot.greendao.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class GreenDaoFactReaderTests {

    FactTypeGroup mTestFactTypeGroup;
    ValueDescriptor mTestValueDescriptor;
    FactType mTestFactType;
    Fact mTestFact;
    private Database mDb;
    private DaoSession mDaoSession;
    private GreenDaoFactReader sut;

    @Before
    public void setUp() throws Exception {

        // TODO: 12.02.2017 copypaste insert test fact
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(RuntimeEnvironment.application, null);
        mDb = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(mDb).newSession();

        FactTypeGroupDao ftgd = mDaoSession.getFactTypeGroupDao();
        ValueDescriptorDao vdd = mDaoSession.getValueDescriptorDao();
        FactTypeDao ftd = mDaoSession.getFactTypeDao();

        mTestFactTypeGroup = new FactTypeGroup(null, "Group", "Gr descr", 0);
        mTestValueDescriptor = new ValueDescriptor(null, "Value name", "descr", "ClassName", "");

        mTestFactType = new FactType(null, "Fact type", "Fact type descr", false, ftgd.insert(mTestFactTypeGroup), vdd.insert(mTestValueDescriptor));
        ftd.insert(mTestFactType);

        mTestFact = new Fact(null, new Date(), new Date(1), 55l, "str val", mTestFactType.getId());

        sut = new GreenDaoFactReader(mDaoSession);
    }

    @Test
    public void should_not_load_old_fact_When_loadFactsfeed() {

        should_load_with_factDate_filter_When_loadFactsfeed_testCase(-1000L, false);
    }

    @Test
    public void should_load_When_loadFactsfeed() {

        should_load_with_factDate_filter_When_loadFactsfeed_testCase(1000L, true);
    }

    private void should_load_with_factDate_filter_When_loadFactsfeed_testCase(long periodBeginDelta, boolean isLoaded) {
        // Given
        Date curDate = new Date();
        Date factDate = new Date(curDate.getTime() - GreenDaoFactReader.FACTSFEED_PERIOD + periodBeginDelta);
        mTestFact.setFactDate(factDate);
        mDaoSession.getFactDao().insert(mTestFact);

        // When
        List<Fact> facts = sut.loadFactsfeed();

        // Then
        assertEquals(isLoaded, facts.contains(mTestFact));
    }
}
