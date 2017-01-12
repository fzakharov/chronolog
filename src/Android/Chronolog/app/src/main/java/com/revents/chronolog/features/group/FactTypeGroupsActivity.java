package com.revents.chronolog.features.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.model.FactTypeGroup;

import javax.inject.Inject;
import javax.inject.Named;

public class FactTypeGroupsActivity extends AppCompatActivity {

    private ResultUiCommand<FactTypeGroup> mAddFactTypeGroupResultUiCommand;

    @Inject
    public void inject(@Named("New") ResultUiCommand<FactTypeGroup> addFactTypeGroupResultUiCommand) {

        mAddFactTypeGroupResultUiCommand = addFactTypeGroupResultUiCommand;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAddFactTypeGroupResultUiCommand.onResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_type_groups);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);
    }

    public void addFactTypeGroupClick(View v) {
        mAddFactTypeGroupResultUiCommand.execute(this);
    }
}
