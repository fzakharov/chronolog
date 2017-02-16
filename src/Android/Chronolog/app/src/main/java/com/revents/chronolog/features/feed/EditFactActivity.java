package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.revents.chronolog.app.DateListener;
import com.revents.chronolog.app.DateTimeProvider;
import com.revents.chronolog.app.TimeDialog;
import com.revents.chronolog.databinding.ActivityEditFactBinding;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;

import java.util.Date;

import javax.inject.Inject;

public class EditFactActivity extends AppCompatActivity implements DateListener {

    static final long ONE_MINUTE_IN_MILLIS = 60000;
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
    private EditText mDescrEt;
    private DateDialog mDateDialog;
    private TimeDialog mTimeDialog;
    private DateTimeProvider mDateTimeProvider;
    private Button mDateBtn;
    private Button mTimeBtn;
    private ActivityEditFactBinding mBinding;

    @Inject
    public void inject(
            IntentExtractor<FactType> extractor,
            FactWriter factWriter,
            ActivityExtractor<Fact, EditFactActivity> factActivityExtractor,
            DateDialog dateDialog,
            TimeDialog timeDialog,
            DateTimeProvider dtProv) {

        mDateTimeProvider = dtProv;
        mTimeDialog = timeDialog;
        mDateDialog = dateDialog;
        mExtractor = extractor;
        mFactWriter = factWriter;
        mFactActivityExtractor = factActivityExtractor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_fact);

        setSupportActionBar(mBinding.toolbar);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);

        mUpdateBtn = mBinding.content.updateBtn;
        mDateBtn = mBinding.content.dateBtn;
        mTimeBtn = mBinding.content.timeBtn;

        onDateChanged(mFactDate);

        mValueEt = mBinding.content.valueEt;
        mValueEt.setText(mFactValue.toString());

        mDescrEt = mBinding.content.descrEt;

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

        // TODO: 26.01.2017 refactor this
        mDescrEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFactDescription = s.toString();
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
        mBinding.content.factTypeTv.setText(mFactType.getName());
    }

    public void updateBtnOnClick(View v) {
        Fact fact = mFactActivityExtractor.extract(this);
        mFactWriter.write(fact);
        finish();
    }

    public void dec15mBtnClick(View v) {
        decreaseFactDate(15);
    }

    public void dec30mBtnClick(View v) {
        decreaseFactDate(30);
    }

    public void dec1hBtnClick(View v) {
        decreaseFactDate(60);
    }

    public void dec1dBtnClick(View v) {
        decreaseFactDate(60 * 24);
    }

    public void timeBtnOnClick(View v) {
        mTimeDialog.show(mFactDate, this, this);
    }

    public void dateBtnOnClick(View v) throws InterruptedException {
        mDateDialog.show(mFactDate, this, this);
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

    public void setFactDate(Date newDate) {
        mFactDate = newDate;
        mDateBtn.setText(mDateTimeProvider.toDateString(mFactDate));
        mTimeBtn.setText(mDateTimeProvider.toTimeString(mFactDate));
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

    @Override
    public void onDateChanged(Date newDate) {
        setFactDate(newDate);
    }

    private void decreaseFactDate(long m) {
        setFactDate(new Date(getFactDate().getTime() - ONE_MINUTE_IN_MILLIS * m));
    }
}
