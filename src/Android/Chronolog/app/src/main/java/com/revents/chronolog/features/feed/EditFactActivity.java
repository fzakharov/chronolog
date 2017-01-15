package com.revents.chronolog.features.feed;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.JavaDateTimeProvider;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.db.greendao.GreenDaoFactWriter;
import com.revents.chronolog.model.DaoSession;
import com.revents.chronolog.model.Fact;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

public class EditFactActivity extends AppCompatActivity {

    @Inject
    public void inject() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);

    }
}
