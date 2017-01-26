package com.revents.chronolog.features.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.ActivityExtractor;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.DateDialog;
import com.revents.chronolog.app.TimeDialog;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import java.util.Date;

import javax.inject.Inject;

public class EditFactActivity extends AppCompatActivity {

    private IntentExtractor<FactType> mExtractor;
    private FactType mFactType;
    private FactWriter mFactWriter;
    private ActivityExtractor<Fact, EditFactActivity> mFactActivityExtractor;
    private Date mFactDate = new Date();
    private Long mFactValue = 1L;
    private String mFactDescription;
    private FactType factType;
    private Long mFactId;
    private Button mUpdateBtn;
    private EditText mValueEt;
    private DateDialog mDateDialog;
    private TimeDialog mTimeDialog;

    @Inject
    public void inject(
            IntentExtractor<FactType> extractor,
            FactWriter factWriter,
            ActivityExtractor<Fact, EditFactActivity> factActivityExtractor,
            DateDialog dateDialog, TimeDialog timeDialog) {

        mTimeDialog = timeDialog;
        mDateDialog = dateDialog;
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

        mUpdateBtn = (Button) findViewById(R.id.updateBtn);

        mValueEt = (EditText) findViewById(R.id.valueEt);
        mValueEt.setText(mFactValue.toString());

        // TODO: 26.01.2017 refactor this
        mValueEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mFactValue = Long.parseLong(s.toString());
                    mUpdateBtn.setEnabled(true);
                } catch (NumberFormatException ex) {
                    mUpdateBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadFactType();
    }

    private void LoadFactType() {
        Intent intent = getIntent();
        mFactType = mExtractor.extract(intent);
        ((TextView) findViewById(R.id.factTypeTv)).setText(mFactType.getName());
    }

    public void updateBtnOnClick(View v) {
        Fact fact = mFactActivityExtractor.extract(this);
        mFactWriter.write(fact);
    }

    public void timeBtnOnClick(View v) {
        if (mTimeDialog.Show(mFactDate))
            mFactDate = mTimeDialog.getSelectedTime();
    }

    public void dateBtnOnClick(View v) {
        if (mDateDialog.Show(mFactDate))
            mFactDate = mDateDialog.getSelectedDate();
    }

    public void increaseBtnClick(View v) {
        Long newValue = getFactValue() + 1;
        mValueEt.setText(newValue.toString());
    }

    public void decreaseBtnClick(View v) {
        Long newValue = getFactValue() - 1;
        mValueEt.setText(newValue.toString());
    }

    public Date getFactDate() {
        return mFactDate;
    }

    public Long getFactValue() {
        return mFactValue;
    }

    public String getFactDescription() {
        return mFactDescription;
    }

    public FactType getFactType() {
        return mFactType;
    }

    public Long getFactId() {
        return mFactId;
    }
}
