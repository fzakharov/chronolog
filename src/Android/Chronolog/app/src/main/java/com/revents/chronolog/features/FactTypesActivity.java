package com.revents.chronolog.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.model.FactType;

import javax.inject.Inject;

public class FactTypesActivity extends AppCompatActivity {

    private ActivityCommand<FactType> mAddFactTypeActivityCommand;

    @Inject
    public void inject(ActivityCommand<FactType> addFactTypeActivityCommand) {

        mAddFactTypeActivityCommand = addFactTypeActivityCommand;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAddFactTypeActivityCommand.onResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_types);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);
    }

    public void addFactTypeClick(View v) {
        mAddFactTypeActivityCommand.execute(this);
    }
}
