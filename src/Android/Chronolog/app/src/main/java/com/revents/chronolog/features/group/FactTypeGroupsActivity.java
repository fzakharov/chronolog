package com.revents.chronolog.features.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.FactTypeGroup;

import javax.inject.Inject;
import javax.inject.Named;

public class FactTypeGroupsActivity extends AppCompatActivity {

    private UiCommand<FactTypeGroup> mAddFactTypeGroupUiCommand;

    @Inject
    public void inject(@Named("New") UiCommand<FactTypeGroup> addFactTypeGroupUiCommand) {

        mAddFactTypeGroupUiCommand = addFactTypeGroupUiCommand;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAddFactTypeGroupUiCommand.onResult(this, requestCode, resultCode, data);
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
        mAddFactTypeGroupUiCommand.execute(this);
    }
}
