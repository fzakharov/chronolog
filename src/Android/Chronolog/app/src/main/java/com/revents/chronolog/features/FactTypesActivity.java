package com.revents.chronolog.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.model.Fact;

import javax.inject.Inject;

public class FactTypesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_types);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addFactTypeClick(View v)
    {
        Intent data = new Intent();
        data.putExtra("FactTypeId", 42l);

        setResult(RESULT_OK, data);
        finish();
    }
}
