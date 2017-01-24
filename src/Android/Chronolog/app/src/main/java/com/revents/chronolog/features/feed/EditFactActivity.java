package com.revents.chronolog.features.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.ActivityExtractor;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import javax.inject.Inject;

public class EditFactActivity extends AppCompatActivity {

    private IntentExtractor<FactType> mExtractor;
    private FactType mFactType;
    private FactWriter mFactWriter;
    private ActivityExtractor<Fact, EditFactActivity> mFactActivityExtractor;

    @Inject
    public void inject(IntentExtractor<FactType> extractor, FactWriter factWriter, ActivityExtractor<Fact, EditFactActivity> factActivityExtractor) {

        mExtractor = extractor;
        mFactWriter = factWriter;
        mFactActivityExtractor = factActivityExtractor;
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

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        mFactType = mExtractor.extract(intent);
        ((TextView) findViewById(R.id.factTypeTv)).setText(mFactType.getName());
    }

    public void updateBtnOnClick(View v) {
        Fact fact = mFactActivityExtractor.extract(this);
        mFactWriter.write(fact);
    }
}
