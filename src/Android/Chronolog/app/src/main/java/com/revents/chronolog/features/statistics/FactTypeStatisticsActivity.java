package com.revents.chronolog.features.statistics;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.RecyclerViewAdapterFactory;
import com.revents.chronolog.databinding.ActivityFactTypeStatisticsBinding;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.FactType;

import javax.inject.Inject;

public class FactTypeStatisticsActivity extends AppCompatActivity {
    private ActivityFactTypeStatisticsBinding mBinding;
    private IntentExtractor<FactType> mFactTypeExtractor;
    private RecyclerViewAdapterFactory<FactType> mRecyclerViewAdapterFactory;

    @Inject
    public void inject(IntentExtractor<FactType> factTypeExtractor, RecyclerViewAdapterFactory<FactType> recyclerViewAdapterFactory) {

        mFactTypeExtractor = factTypeExtractor;
        mRecyclerViewAdapterFactory = recyclerViewAdapterFactory;
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