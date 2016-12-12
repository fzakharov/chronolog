package com.revents.chronolog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

import java.security.InvalidParameterException;
import java.util.List;

// TODO: 07.12.2016 https://github.com/greenrobot/greenDAO/blob/master/examples/DaoExample/src/main/java/org/greenrobot/greendao/example/NotesAdapter.java

public class FactsViewActivity extends AppCompatActivity {

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FactsViewActivity.this, FactTypesListActivity.class);
                startActivity(intent);

                ReloadFacts();

//                Snackbar.make(view, fact.getFactDate().toString(), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        daoSession = ((App) getApplication()).getDaoSession();

        fillFactTypes();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ReloadFacts();
    }

    private void ReloadFacts() {
        FactDao factDao = daoSession.getFactDao();

        // TODO: 11.12.2016 to facts reader
        // query all notes, sorted a-z by their text
        Query<Fact> factsQuery = factDao.queryBuilder().orderDesc(FactDao.Properties.FactDate).build();

        List<Fact> facts = factsQuery.list();
        FactsAdapter adapter = new FactsAdapter(this, facts);

        ListView lvEvents = (ListView) findViewById(R.id.factsList);
        lvEvents.setAdapter(adapter);
    }

    private void safeAddFactType(String name, String description) {
        FactType ft = new FactType();
        FactTypeDao factTypeDao = daoSession.getFactTypeDao();

        List list = factTypeDao.queryBuilder()
                .where(FactTypeDao.Properties.Name.eq(name))
                .list();

        if (list.size() > 0)
            return;

        ft.setName(name);
        ft.setDescription(description);
        factTypeDao.insert(ft);
    }

    String FACT_COFFE = "Кофе";
    String FACT_WAKEUP = "Проснулся";
    String FACT_WENT_TO_SLEEP = "Лег спать";

    private void fillFactTypes() {

        // TODO: 11.12.2016 Use in app load. Move to db initialization 
        safeAddFactType(FACT_COFFE, "Выпил чашку кофе");
        safeAddFactType(FACT_WAKEUP, "Подъем - качество сна 1-3");
        safeAddFactType(FACT_WENT_TO_SLEEP, "Лег спать - состояние 1-3");
    }
}