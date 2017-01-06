package com.revents.chronolog.features.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.model.Fact;

import javax.inject.Inject;

public class FactsfeedActivity extends AppCompatActivity {

    private UiCommand<Fact> mAddFactUiCommand;

    @Inject
    public void inject(UiCommand<Fact> addFactUiCommand) {
        mAddFactUiCommand = addFactUiCommand;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factsfeed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);

    }

    public void addFactClick(View v) {
        mAddFactUiCommand.execute(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fact fact = mAddFactUiCommand.onResult(this, requestCode, resultCode, data);
        if (fact != null)
            setTitle(fact.getStrValue());
    }

    public void setAdapter(BaseAdapter adapter) {
        ListView lvFeed = (ListView) findViewById(R.id.factsfeedListView);
        lvFeed.setAdapter(adapter);
    }
}
