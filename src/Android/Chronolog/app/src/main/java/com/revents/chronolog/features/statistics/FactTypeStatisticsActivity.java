package com.revents.chronolog.features.statistics;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;

import com.revents.chronolog.R;
import com.revents.chronolog.app.*;
import com.revents.chronolog.databinding.ActivityFactTypeStatisticsBinding;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.FactType;

import javax.inject.Inject;

public class FactTypeStatisticsActivity extends AppCompatActivity {
	public static final int PERIOD_WEEK = 7;
	public static final int PERIOD_2WEEK = 14;
	public static final int PERIOD_MONTH = 31;
	public static final int PERIOD_3MONTH = 90;
	public static final int PERIOD_6MONTH = 180;

	private ActivityFactTypeStatisticsBinding mBinding;
	private IntentExtractor<FactType> mFactTypeExtractor;
	private RecyclerViewAdapterFactory<FactType> mRecyclerViewAdapterFactory;
	private DataContext mDataContext;
	private FactType mFactType;

	@Inject
	public void inject(IntentExtractor<FactType> factTypeExtractor,
					   RecyclerViewAdapterFactory<FactType> recyclerViewAdapterFactory,
					   DataContext dataContext) {

		mFactTypeExtractor = factTypeExtractor;
		mRecyclerViewAdapterFactory = recyclerViewAdapterFactory;
		mDataContext = dataContext;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_fact_type_statistics);
		setSupportActionBar(mBinding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
		appComp.inject(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);

		setPeriodDays(14, R.id.mi2Weeks);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.miWeek:
				return setPeriodDays(PERIOD_WEEK, R.id.miWeek);
			case R.id.mi2Weeks:
				return setPeriodDays(PERIOD_2WEEK, R.id.mi2Weeks);
			case R.id.miMonth:
				return setPeriodDays(PERIOD_MONTH, R.id.miMonth);
			case R.id.mi3Month:
				return setPeriodDays(PERIOD_3MONTH, R.id.mi3Month);
			case R.id.mi6Month:
				return setPeriodDays(PERIOD_6MONTH, R.id.mi6Month);
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private Boolean setPeriodDays(int days, int mId) {
		mDataContext.setPeriodDays(days);

		setMenuItemEnabled(R.id.miWeek, mId);
		setMenuItemEnabled(R.id.mi2Weeks, mId);
		setMenuItemEnabled(R.id.miMonth, mId);
		setMenuItemEnabled(R.id.mi3Month, mId);
		setMenuItemEnabled(R.id.mi6Month, mId);

		setRvAdapter();

		return true;
	}

	private void setMenuItemEnabled(int mId, int disabledId) {
		MenuItem mItem = mBinding.toolbar.getMenu().findItem(mId);
		mItem.setEnabled(mId != disabledId);
	}

	@Override
	protected void onResume() {
		super.onResume();

		RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
		mBinding.statRv.setLayoutManager(lm);
		mFactType = mFactTypeExtractor.extract(getIntent());

		setRvAdapter();
	}

	private void setRvAdapter() {
		RecyclerView.Adapter adapter = mRecyclerViewAdapterFactory.create(mFactType);
		mBinding.statRv.setAdapter(adapter);
	}
}