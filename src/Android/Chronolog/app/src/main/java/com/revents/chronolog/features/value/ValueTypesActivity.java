package com.revents.chronolog.features.value;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.HeadedItemsListAdapter;
import com.revents.chronolog.features.HeadedLvItem;
import com.revents.chronolog.model.ValueDescriptor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

// TODO: 07.01.2017 Call ValueDescriptor editor activity
public class ValueTypesActivity extends AppCompatActivity {

    private ValueTypesProvider mValueTypesProvider;
    private FactWriter mFactWriter;
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

        // TODO: 07.01.2017 introduce method
        mValueTypesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id) {

                ValueType vt = mValueTypesProvider.getValueTypes()[position];

                ValueDescriptor vd = new ValueDescriptor(null, vt.name, vt.description, vt.className, "");
                mFactWriter.write(vd);
            }
        });
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
    public void inject(ValueTypesProvider valueTypesProvider, FactWriter factWriter) {

        mValueTypesProvider = valueTypesProvider;
        mFactWriter = factWriter;
    }
}
