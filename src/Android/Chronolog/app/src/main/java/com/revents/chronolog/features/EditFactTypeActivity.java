package com.revents.chronolog.features;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.revents.chronolog.R;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.model.DaoSession;

public class EditFactTypeActivity extends AppCompatActivity {

    public static final String FACT_TYPE_ID_EXTRA_NAME = "FactTypeId";
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fact_type);

        daoSession = ((ChronologApp) getApplication()).getDaoSession();

        Button addBtn = (Button) findViewById(R.id.addFactTypeBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = getFromText(R.id.nameTxt);
                String descr = getFromText(R.id.descrTxt);

                // TODO: 19.12.2016 restore code
                //daoSession.getFactTypeDao().insert(new FactType(null, name, descr));
                finish();
            }
        });

        Button closeBtn = (Button) findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.nameTxt).requestFocus();
    }

    private String getFromText(@IdRes int id)
    {
        EditText txt = (EditText) findViewById(id);

        return txt.getText().toString();
    }
}
