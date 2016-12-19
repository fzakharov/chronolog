package com.revents.chronolog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.revents.chronolog.Model.Fact;

import java.text.SimpleDateFormat;
import java.util.List;

public class FactsAdapter extends BaseAdapter {

    private final SimpleDateFormat mWeekFormat;
    Context context;
    List<Fact> data;
    SimpleDateFormat mTimeFormat;
    private static LayoutInflater inflater = null;

    public FactsAdapter(Context context, List<Fact> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mTimeFormat = new SimpleDateFormat("HH:mm");
        mWeekFormat = new SimpleDateFormat("EEEE");
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Fact fact = data.get(position);

        if (vi == null)
            vi = inflater.inflate(R.layout.fact_listview_item, null);

        TextView text = (TextView) vi.findViewById(R.id.header);
        text.setText(fact.getFactType().getName());

        text = (TextView) vi.findViewById(R.id.valueTxt);
        text.setText(fact.getLongValue().toString());

        text = (TextView) vi.findViewById(R.id.time);
        text.setText(mTimeFormat.format(fact.getFactDate()));

        text = (TextView) vi.findViewById(R.id.weekDay);
        text.setText(mWeekFormat.format(fact.getFactDate()));
        return vi;
    }
}
