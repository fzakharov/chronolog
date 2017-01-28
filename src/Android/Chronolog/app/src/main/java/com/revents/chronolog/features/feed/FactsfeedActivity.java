package com.revents.chronolog.features.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.model.Fact;

import javax.inject.Inject;

public class FactsfeedActivity extends AppCompatActivity {

    private ResultUiCommand<Fact> mAddFactResultUiCommand;
    private FactReader mFactReader;

    @Inject
    public void inject(ResultUiCommand<Fact> addFactResultUiCommand, FactReader factReader) {
        mAddFactResultUiCommand = addFactResultUiCommand;
        mFactReader = factReader;
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

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView rv = (RecyclerView) findViewById(R.id.factsfeedRv);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        FactsfeedRvAdapter adapter = new FactsfeedRvAdapter(mFactReader.loadFactsfeed());
        rv.setAdapter(adapter);
    }

    public void addFactClick(View v) {
        mAddFactResultUiCommand.execute(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fact fact = mAddFactResultUiCommand.onResult(this, requestCode, resultCode, data);
        if (fact != null)
            setTitle(fact.getStrValue());
    }
}
