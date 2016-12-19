package com.revents.chronolog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.revents.chronolog.Model.DaoSession;
import com.revents.chronolog.Model.Fact;
import com.revents.chronolog.Model.FactDao;
import com.revents.chronolog.Model.FactType;
import com.revents.chronolog.Model.FactTypeDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

// TODO: 07.12.2016 https://github.com/greenrobot/greenDAO/blob/master/examples/DaoExample/src/main/java/org/greenrobot/greendao/example/NotesAdapter.java

public class FactsViewActivity extends AppCompatActivity {

    private DaoSession daoSession;
    ListView lvFacts;
    private List<Fact> mFacts;
    private FactsAdapter mAdapter;

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

        lvFacts = (ListView) findViewById(R.id.factsList);

        lvFacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {

                final Fact f = daoSession.getFactDao().load(id);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                daoSession.getFactDao().delete(f);
                                mFacts.remove(position);
                                mAdapter.notifyDataSetChanged();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                String msg = f.getFactDate().toString() + " " + f.getFactType().getName();

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Удалить " + msg + "?")
                        .setPositiveButton("Да", dialogClickListener)
                        .setNegativeButton("Нет", dialogClickListener)
                        .show();

                return false;
            }
        });

        fillFactTypesDefaultsToDb();
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

        mFacts = factsQuery.list();
        mAdapter = new FactsAdapter(this, mFacts);
        lvFacts.setAdapter(mAdapter);
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

    private void fillFactTypesDefaultsToDb() {

        // TODO: 11.12.2016 Use in app load. Move to db initialization 
        safeAddFactType(FACT_COFFE, "Выпил чашку кофе");
        safeAddFactType(FACT_WAKEUP, "Подъем - качество сна 1-3");
        safeAddFactType(FACT_WENT_TO_SLEEP, "Лег спать - состояние 1-3");
    }
}
