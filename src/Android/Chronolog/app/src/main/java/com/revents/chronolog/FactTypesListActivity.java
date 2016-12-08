package com.revents.chronolog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class FactTypesListActivity extends AppCompatActivity {

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoSession = ((App)getApplication()).getDaoSession();

        setContentView(R.layout.activity_fact_types_list);

        FactTypeDao factTypeDao = daoSession.getFactTypeDao();
        List list = factTypeDao.loadAll();

        FactTypesAdapter adapter = new FactTypesAdapter(this, list);

        ListView lvEvents = (ListView) findViewById(R.id.factTypesList);
        lvEvents.setAdapter(adapter);
    }
}
