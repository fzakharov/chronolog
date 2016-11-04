package com.revents.chronolog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Objects;

public class EventListViewItemAdapter extends BaseAdapter {

    Context context;
    EventRecordDto[] data;
    private static LayoutInflater inflater = null;

    public EventListViewItemAdapter(Context context, EventRecordDto[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.event_listview_item, null);
        TextView text = (TextView) vi.findViewById(R.id.header);
        text.setText(Objects.toString(data[position].eventTypeId));

        text = (TextView) vi.findViewById(R.id.text);
        text.setText(Objects.toString(data[position].eventTime));
        return vi;
    }
}
