package com.revents.chronolog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FactTypesAdapter extends BaseAdapter {

    Context context;
    List<FactType> data;
    private static LayoutInflater inflater = null;

    public FactTypesAdapter(Context context, List<FactType> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return  data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        FactType fact = data.get(position);

        if (vi == null)
            vi = inflater.inflate(R.layout.event_listview_item, null);
        TextView text = (TextView) vi.findViewById(R.id.header);
        text.setText( fact.getName());

        text = (TextView) vi.findViewById(R.id.text);
        text.setText( fact.getDescription());
        return vi;
    }
}
