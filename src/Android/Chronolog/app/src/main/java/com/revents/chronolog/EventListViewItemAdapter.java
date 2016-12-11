package com.revents.chronolog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.Objects;

public class EventListViewItemAdapter extends BaseAdapter {

    Context context;
    EventRecordDto[] data;
    private static LayoutInflater inflater = null;

    public EventListViewItemAdapter(Context context, EventRecordDto[] data) {
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
        return data[position].id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.event_listview_item, null);
        TextView text = (TextView) vi.findViewById(R.id.header);
        text.setText(eventTypeIdToDisplayName(data[position].eventTypeId));

        text = (TextView) vi.findViewById(R.id.text);
        //text.setText(getDate(data[position].eventTime*1000));
        text.setText(data[position].eventTimeStr);
        return vi;
    }

    private String getDate(long sqliteDateTime)
    {
        Date addedOn = new Date(sqliteDateTime);
        return addedOn.toString();
    }

    private String eventTypeIdToDisplayName(long eventTypeId)
    {
        switch ((int) eventTypeId) {
            case EventEntry.EVENT_TYPE_ID_BREAKFASTSTAR:
                return "Завтрак";
            case EventEntry.EVENT_TYPE_ID_COFFEE:
                return "Кофе";
            case EventEntry.EVENT_TYPE_ID_DINNERSTAR:
                return "Ужин";
            case EventEntry.EVENT_TYPE_ID_LUNCHSTAR:
                return "Обед";
            case EventEntry.EVENT_TYPE_ID_TOSLEEP:
                return "Лег спать";
            case EventEntry.EVENT_TYPE_ID_WAKEUP:
                return "Проснулся";
            default:
                return "неизвестно";
        }
    }
}
