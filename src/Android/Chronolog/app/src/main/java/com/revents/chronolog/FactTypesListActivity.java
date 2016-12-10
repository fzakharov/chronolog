package com.revents.chronolog;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class FactTypesListActivity extends AppCompatActivity {

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_types_list);

        daoSession = ((App) getApplication()).getDaoSession();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FactTypesListActivity.this, EditFactTypeActivity.class);
                startActivity(intent);

            }
        });

        FactTypeDao factTypeDao = daoSession.getFactTypeDao();
        List list = factTypeDao.loadAll();

        FactTypesAdapter adapter = new FactTypesAdapter(this, list);

        ListView lvEvents = (ListView) findViewById(R.id.factTypesList);
        lvEvents.setAdapter(adapter);

        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Fact fact = addFact(id);

                finish();
            }
        });
    }

    private Fact addFact(long fatcTypeId) {
        JavaDateTimeProvider dateTimeProvider = new JavaDateTimeProvider();
        GreenDaoFactWriter wr = new GreenDaoFactWriter(dateTimeProvider, daoSession);

        Fact fact = new Fact(null, null, dateTimeProvider.getDate(), 1, "", fatcTypeId);
        wr.write(fact);

        return fact;
    }
}
