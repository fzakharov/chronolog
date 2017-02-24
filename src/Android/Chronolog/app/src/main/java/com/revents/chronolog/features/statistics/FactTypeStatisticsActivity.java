package com.revents.chronolog.features.statistics;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.databinding.ActivityFactTypeStatisticsBinding;

import javax.inject.Inject;

public class FactTypeStatisticsActivity extends AppCompatActivity {

    public static final String FACT_TYPE_ID_EXTRA_NAME = "FactTypeId";
    private ActivityFactTypeStatisticsBinding mBinding;

    @Inject
    public void inject() {

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
}