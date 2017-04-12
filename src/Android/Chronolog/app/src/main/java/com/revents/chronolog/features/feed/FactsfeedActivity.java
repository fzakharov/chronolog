package com.revents.chronolog.features.feed;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;

import com.revents.chronolog.*;
import com.revents.chronolog.app.*;
import com.revents.chronolog.db.*;
import com.revents.chronolog.model.*;
import com.revents.chronolog.ui.*;
import com.revents.chronolog.ui.recyclerview.*;

import javax.inject.*;

public class FactsfeedActivity extends AppCompatActivity implements EventListener<EventArgs<Pair<Boolean, Fact>>>, View.OnLongClickListener, View.OnClickListener {

	private UiCommand mAddFactUiCommand;
	private FactsfeedPresenter mFactsfeedPresenter;
	private YesNoDialog mYesNoDialog;
	private FactWriter mFactWriter; // todo move dialog and delete operation to action
	private UiAction<Fact> mFactClickAction;

	@Inject
	public void inject(
			@Named(CommandTypes.SELECT) UiCommand addFactUiCommand,
			UiAction<Fact> factClickAction,
			FactsfeedPresenter factsfeedPresenter,
			FactWriter factWriter,
			YesNoDialog yesNoDialog) {

		mAddFactUiCommand = addFactUiCommand;
		mFactClickAction = factClickAction;
		mFactsfeedPresenter = factsfeedPresenter;
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

		ItemTypeDispatcherRecyclerViewAdapter adapter = new ItemTypeDispatcherRecyclerViewAdapter<>(
				mFactsfeedPresenter.loadFactsfeed(),
				new FactsfeedRecyclerViewItemProvider());

		adapter.setItemOnClickListener(v -> onClick(v));
		adapter.setItemOnLongClickListener(v -> onLongClick(v));

		rv.setAdapter(adapter);
	}

	public void addFactClick(View v) {
		mAddFactUiCommand.execute(this);
	}

	@Override
	public void onEvent(EventArgs<Pair<Boolean, Fact>> args) {
		if (args.getData().first != true)
			return;

		mFactWriter.delete(args.getData().second);
		reload();
	}

	public boolean onLongClick(View v) {

		FactViewHolder holder = (FactViewHolder) v.getTag();
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

	public void onClick(View v) {
		FactViewHolder holder = (FactViewHolder) v.getTag();

		mFactClickAction.execute(this, holder.getFact());
	}
}
