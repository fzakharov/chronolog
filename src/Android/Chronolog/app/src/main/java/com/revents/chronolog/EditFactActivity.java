package com.revents.chronolog;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// TODO: 11.12.2016 date Dialog http://startandroid.ru/ru/uroki/vse-uroki-spiskom/118-urok-59-dialogi-datepickerdialog.html
public class EditFactActivity extends AppCompatActivity {

    private DaoSession daoSession;
    private long mfactTypeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fact);

        daoSession = ((App) getApplication()).getDaoSession();
        mfactTypeId = getIntent().getLongExtra("factTypeId", -1);

        Button addBtn = (Button) findViewById(R.id.addFactBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String name = getFromText(R.id.nameTxt);
                addFact(mfactTypeId);
                finish();
            }
        });

        Button closeBtn = (Button) findViewById(R.id.cancelBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addFact(long fatcTypeId) {
        JavaDateTimeProvider dateTimeProvider = new JavaDateTimeProvider();
        GreenDaoFactWriter wr = new GreenDaoFactWriter(dateTimeProvider, daoSession);

        Fact fact = new Fact(null, null, dateTimeProvider.getDate(), 1, "", fatcTypeId);
        wr.write(fact);
    }

    private String getFromText(@IdRes int id)
    {
        EditText txt = (EditText) findViewById(id);

        return txt.getText().toString();
    }
}
