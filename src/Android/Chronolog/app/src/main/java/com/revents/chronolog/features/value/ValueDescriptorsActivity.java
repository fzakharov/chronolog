package com.revents.chronolog.features.value;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.CommandTypes;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.model.ValueDescriptor;

import javax.inject.Inject;
import javax.inject.Named;

public class ValueDescriptorsActivity extends AppCompatActivity {
    private ResultUiCommand<ValueDescriptor> mAddValueDescriptorResultUiCommand;

    @Inject
    public void inject(@Named(CommandTypes.NEW) ResultUiCommand<ValueDescriptor> addValueDescriptorResultUiCommand) {

        mAddValueDescriptorResultUiCommand = addValueDescriptorResultUiCommand;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAddValueDescriptorResultUiCommand.onResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_descriptors);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);
    }

    public void addValueDescriptorClick(View v) {
        mAddValueDescriptorResultUiCommand.execute(this);
    }
}
