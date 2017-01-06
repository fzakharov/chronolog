package com.revents.chronolog.features.type;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.model.FactType;

import javax.inject.Inject;

public class FactTypesActivity extends AppCompatActivity {

    private UiCommand<FactType> mAddFactTypeUiCommand;

    @Inject
    public void inject(UiCommand<FactType> addFactTypeUiCommand) {

        mAddFactTypeUiCommand = addFactTypeUiCommand;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAddFactTypeUiCommand.onResult(this, requestCode, resultCode, data);
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
        mAddFactTypeUiCommand.execute(this);
    }
}
