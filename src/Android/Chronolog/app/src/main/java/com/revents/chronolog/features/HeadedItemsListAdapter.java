package com.revents.chronolog.features;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.revents.chronolog.R;

import java.util.List;

public class HeadedItemsListAdapter extends BaseAdapter {
    private final List<HeadedLvItem> mData;
    private final LayoutInflater mInflater;

    public HeadedItemsListAdapter(Context context, List<HeadedLvItem> data) {
        mData = data;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        HeadedLvItem item = mData.get(position);

        if (vi == null)
            vi = mInflater.inflate(R.layout.headed_listview_item, null);

        TextView text = (TextView) vi.findViewById(R.id.headerTv);
        text.setText(item.header);

        text = (TextView) vi.findViewById(R.id.contentTv);
        text.setText(item.content);

        return vi;
    }

}
