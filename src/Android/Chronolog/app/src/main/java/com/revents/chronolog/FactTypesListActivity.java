package com.revents.chronolog;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.revents.chronolog.Model.DaoSession;
import com.revents.chronolog.Model.FactTypeDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

// TODO: 11.12.2016 select fact type activity 
public class FactTypesListActivity extends AppCompatActivity {

    private DaoSession daoSession;
    private ListView mTypesList;

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

        mTypesList = (ListView) findViewById(R.id.factTypesList);
        mTypesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                addFact(id);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        FactTypeDao factTypeDao = daoSession.getFactTypeDao();
        List list = factTypeDao.loadAll();

        FactTypesAdapter adapter = new FactTypesAdapter(this, list);
        mTypesList.setAdapter(adapter);
    }

    private void addFact(long factTypeId) {

        Intent intent = new Intent(FactTypesListActivity.this, EditFactActivity.class);
        intent.putExtra("factTypeId", factTypeId);
        startActivity(intent);
    }
}
