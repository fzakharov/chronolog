package com.revents.chronolog.features.value;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.features.HeadedItemsListAdapter;
import com.revents.chronolog.features.HeadedLvItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ValueTypesActivity extends AppCompatActivity {

    private ValueTypesProvider mValueTypesProvider;
    private ListView mValueTypesLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_types);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);

        mValueTypesLv = (ListView) findViewById(R.id.valueTypesLv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<HeadedLvItem> data = generateData();
        mValueTypesLv.setAdapter(new HeadedItemsListAdapter(this, data));
    }

    private List<HeadedLvItem> generateData() {
        List<HeadedLvItem> data = new ArrayList<>();

        for (ValueType vt : mValueTypesProvider.getValueTypes())
            data.add(new HeadedLvItem(vt.className, vt.name, vt.description));

        return data;
    }

    @Inject
    public void inject(ValueTypesProvider valueTypesProvider) {

        mValueTypesProvider = valueTypesProvider;
    }
}
