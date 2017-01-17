package com.revents.chronolog.features.type;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.model.FactType;

import java.util.List;

public class FactTypesRvAdapter extends RecyclerView.Adapter<FactTypesRvAdapter.FactTypeViewHolder> {
    private List<FactType> mTypes;

    public FactTypesRvAdapter(List<FactType> types) {
        mTypes = types;
    }

    @Override
    public FactTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.facttype_rv_item, parent, false);

        return new FactTypeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FactTypeViewHolder holder, int position) {
        FactType record = mTypes.get(position);
        holder.name.setText(record.getName());
    }

    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    public static class FactTypeViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;

        FactTypeViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.factTypeNameTv);
        }
    }
}
