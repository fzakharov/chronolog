package com.revents.chronolog.features.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.AppModule_ProvideFactWriterFactory;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.EventArgs;
import com.revents.chronolog.app.EventListener;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.app.YesNoDialog;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.Fact;

import javax.inject.Inject;

public class FactsfeedActivity extends AppCompatActivity implements EventListener<EventArgs<Pair<Boolean, Fact>>>, View.OnLongClickListener {

    private ResultUiCommand<Fact> mAddFactResultUiCommand;
    private FactReader mFactReader;
    private YesNoDialog mYesNoDialog;
    private FactWriter mFactWriter;

    @Inject
    public void inject(ResultUiCommand<Fact> addFactResultUiCommand, FactReader factReader, FactWriter factWriter, YesNoDialog yesNoDialog) {
        mAddFactResultUiCommand = addFactResultUiCommand;
        mFactReader = factReader;
        mYesNoDialog = yesNoDialog;
        mFactWriter = factWriter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factsfeed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppComponent appComp = ((ChronologApp) getApplication()).getAppComponent();
        appComp.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    private void reload() {
        // TODO: 29.01.2017 move to onCreate
        RecyclerView rv = (RecyclerView) findViewById(R.id.factsfeedRv);
        rv.setLongClickable(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        FactsfeedRvAdapter adapter = new FactsfeedRvAdapter(mFactReader.loadFactsfeed(), this);
        rv.setAdapter(adapter);
    }

    public void addFactClick(View v) {
        mAddFactResultUiCommand.execute(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fact fact = mAddFactResultUiCommand.onResult(this, requestCode, resultCode, data);
        if (fact != null)
            setTitle(fact.getStrValue());
    }

    @Override
    public void onEvent(EventArgs<Pair<Boolean, Fact>> args) {
        if (args.getData().first != true)
            return;

        mFactWriter.delete(args.getData().second);
        reload();
    }

    @Override
    public boolean onLongClick(View v) {

        FactsfeedRvAdapter.FactViewHolder holder = (FactsfeedRvAdapter.FactViewHolder) v.getTag();
        Fact f = holder.getFact();

        String msg =
                "Удалить " +
                        f.getId() + " " +
                        f.getFactDate().toString() + " " +
                        f.getFactType().getName() +
                        "?";

        mYesNoDialog.show(this, f, msg, this);

        return true;
    }
}
