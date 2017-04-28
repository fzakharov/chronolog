package com.revents.chronolog.features.statistics;

import android.content.Intent;
import android.databinding.*;
import android.support.v7.widget.*;
import android.view.*;

import com.revents.chronolog.R;
import com.revents.chronolog.app.*;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.FactType;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.fakes.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FactTypeStatisticsActivityRoboTests extends ActivityRoboTestsBase<FactTypeStatisticsActivity> {

	private RecyclerViewAdapterFactory<FactType> mRecyclerViewAdapterFactory;
	private IntentExtractor<FactType> mFactTypeExtractor;
	private Intent mIntent;
	private FactType mFactType;
	private DataContext mDataContext;
	private Toolbar mToolbar;

	@Before
	public void setUp() throws Exception {
		mFactType = new FactType();
		mFactTypeExtractor = mock(IntentExtractor.class);
		mIntent = new Intent();
		mRecyclerViewAdapterFactory = mock(RecyclerViewAdapterFactory.class);
		mDataContext = mock(DataContext.class);

		when(mFactTypeExtractor.extract(mIntent))
				.thenReturn(mFactType);

		sutBuilder = Robolectric.buildActivity(FactTypeStatisticsActivity.class);
		sut = sutBuilder.withIntent(mIntent).get();

		inject(sut);

		sutBuilder.create().start();

		mToolbar = (Toolbar) sut.findViewById(R.id.toolbar);
		sut.onCreateOptionsMenu(mToolbar.getMenu());
	}

	@Test
	public void should_DataContext_setPeriod_When_miSelected() {
		MenuItem miWeek = getMenuItem(R.id.miWeek);
		MenuItem mi2Weeks = getMenuItem(R.id.mi2Weeks);
		MenuItem miMonth = getMenuItem(R.id.miMonth);
		MenuItem mi3Month = getMenuItem(R.id.mi3Month);
		MenuItem mi6Month = getMenuItem(R.id.mi6Month);

		should_DataContext_setPeriod_When_miSelected_test_case(miWeek, FactTypeStatisticsActivity.PERIOD_WEEK);
		should_DataContext_setPeriod_When_miSelected_test_case(mi2Weeks, FactTypeStatisticsActivity.PERIOD_2WEEK);
		should_DataContext_setPeriod_When_miSelected_test_case(miMonth, FactTypeStatisticsActivity.PERIOD_MONTH);
		should_DataContext_setPeriod_When_miSelected_test_case(mi3Month, FactTypeStatisticsActivity.PERIOD_3MONTH);
		should_DataContext_setPeriod_When_miSelected_test_case(mi6Month, FactTypeStatisticsActivity.PERIOD_6MONTH);
	}

	@Test
	public void should_set_rv_adapter_by_FactType_When_onResume() {
		// Given
		RecyclerView.Adapter expected = mock(RecyclerView.Adapter.class);
		when(mRecyclerViewAdapterFactory.create(mFactType))
				.thenReturn(expected);

		// When
		sut.onResume();

		// Then
		assertThat(((RecyclerView) viewById(R.id.statRv))
				.getAdapter())
				.isEqualTo(expected);
	}

	MenuItem getMenuItem(int rId){
		return mToolbar.getMenu().findItem(rId);
	}

	public void should_DataContext_setPeriod_When_miSelected_test_case(MenuItem mi, int expectedPeriod) {
		// Given
		reset(mDataContext);

		// When
		sut.onOptionsItemSelected(mi);

		// Then
		verify(mDataContext).setPeriodDays(expectedPeriod);
		assertThat(mi.isEnabled()).isFalse();
	}

	private void inject(final FactTypeStatisticsActivity activity) {
		ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
		AppComponent cmp = app.getAppComponent();

		doAnswer(invocation -> {

			activity.inject(mFactTypeExtractor, mRecyclerViewAdapterFactory, mDataContext);
			return null;
		}).when(cmp).inject(sut);
	}
}
