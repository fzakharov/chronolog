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
    private ActivityFactTypeStatisticsBinding mBinding;
    private IntentExtractor<FactType> mFactTypeExtractor;
    private RecyclerViewAdapterFactory<FactType> mRecyclerViewAdapterFactory;
	private DataContext mDataContext;

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.miWeek:
				return setPeriodDays(7);
			case R.id.mi2Weeks:
				return setPeriodDays(14);
			case R.id.miMonth:
				return setPeriodDays(31);
			default:
				return super.onOptionsItemSelected(item);
		}
    }

	private Boolean setPeriodDays(int days) {
		mDataContext.setPeriodDays(days);
		return true;
	}

	@Override
    protected void onResume() {
        super.onResume();

        FactType factType = mFactTypeExtractor.extract(getIntent());

        RecyclerView rv = mBinding.statRv;

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        RecyclerView.Adapter adapter = mRecyclerViewAdapterFactory.create(factType);
        rv.setAdapter(adapter);
    }
}