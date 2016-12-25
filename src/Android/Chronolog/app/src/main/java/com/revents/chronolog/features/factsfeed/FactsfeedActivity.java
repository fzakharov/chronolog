package com.revents.chronolog.features.factsfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.Command;

import javax.inject.Inject;

public class FactsfeedActivity extends AppCompatActivity {

    private Command mAddFactCommand;

    @Inject
    public void inject(Command addFactCommand) {
        mAddFactCommand = addFactCommand;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_factsfeed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppComponent appcomp = ((ChronologApp) getApplication()).getAppComponent();
        appcomp.inject(this);

    }

    public void addFactClick(View v) {
        mAddFactCommand.execute();
    }

    public void setAdapter(BaseAdapter adapter) {
        ListView lvFeed = (ListView) findViewById(R.id.factsfeedListView);
        lvFeed.setAdapter(adapter);
    }
}
