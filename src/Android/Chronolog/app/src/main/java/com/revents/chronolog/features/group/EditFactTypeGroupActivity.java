package com.revents.chronolog.features.group;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;

import javax.inject.Inject;

public class EditFactTypeGroupActivity extends AppCompatActivity {

    @Inject
    public void inject() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fact_type_group);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);
    }
}
