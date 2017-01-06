package com.revents.chronolog.features.type;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.CommandTypes;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

import javax.inject.Inject;
import javax.inject.Named;

public class EditFactTypeActivity extends AppCompatActivity {

    private UiCommand<FactTypeGroup> mSelectFactTypeGroup;
    private UiCommand<ValueDescriptor> mSelectValueDescriptor;

    @Inject
    public void inject(
            @Named(CommandTypes.SELECT) UiCommand<FactTypeGroup> selectFactTypeGroupCommand,
            @Named(CommandTypes.SELECT) UiCommand<ValueDescriptor> selectValueDescriptorCommand) {
        mSelectFactTypeGroup = selectFactTypeGroupCommand;
        mSelectValueDescriptor = selectValueDescriptorCommand;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fact_type);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FactTypeGroup ftg = mSelectFactTypeGroup.onResult(this, requestCode, resultCode, data);
        ((TextView) findViewById(R.id.groupTv)).setText(ftg.getName());

    }

    public void selectGroupClick(View v) {
        mSelectFactTypeGroup.execute(this);
    }

    public void selectValueDescriptorClick(View v) {
        mSelectValueDescriptor.execute(this);
    }
}
