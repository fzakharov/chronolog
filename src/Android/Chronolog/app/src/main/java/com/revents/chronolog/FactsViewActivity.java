package com.revents.chronolog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

import java.util.Date;
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
                Fact  fact = addFact();

                ReloadFacts();

                Snackbar.make(view, fact.getFactDate().toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "fatcs-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        ReloadFacts();
    }

    private void ReloadFacts() {
        FactDao factDao = daoSession.getFactDao();

        // query all notes, sorted a-z by their text
        Query<Fact> factsQuery = factDao.queryBuilder().orderDesc(FactDao.Properties.FactDate).build();

        List<Fact> facts = factsQuery.list();
        FactsAdapter adapter = new FactsAdapter(this, facts);

        ListView lvEvents = (ListView) findViewById(R.id.factsList);
        lvEvents.setAdapter(adapter);
    }

    private Fact addFact()
    {
        String coffeName = "Кофе";
        FactType ft = new FactType();
        FactTypeDao factTypeDao = daoSession.getFactTypeDao();
        List list = factTypeDao.queryBuilder().where(FactTypeDao.Properties.Name.eq(coffeName)).list();
        if (list.size() == 0)
        {
            ft.setName(coffeName);
            ft.setDescription("Выпил чашку кофе");
            factTypeDao.insert(ft);
        }
        else
        {
            ft = (FactType) list.get(0);
        }

        JavaDateTimeProvider dateTimeProvider = new JavaDateTimeProvider();
        GreenDaoFactWriter wr = new GreenDaoFactWriter(dateTimeProvider, daoSession);

        Fact fact = new Fact(null, null, dateTimeProvider.getDate(), 1, "", ft.getId());
        wr.write(fact);

        return fact;
    }
}
