package com.revents.chronolog.features.type;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.features.feed.EditFactActivity;
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

        FactTypeViewHolder ftvh = new FactTypeViewHolder(v);

        v.setOnClickListener(ftvh);

        return ftvh;
    }

    @Override
    public void onBindViewHolder(FactTypeViewHolder holder, int position) {
        FactType record = mTypes.get(position);
        holder.name.setText(record.getName());
        holder.descr.setText(record.getDescription());
        holder.factTypeId = record.getId();
    }

    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    public class FactTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView name;
        private final TextView descr;
        public Long factTypeId;

        FactTypeViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.factTypeNameTv);
            descr = (TextView) itemView.findViewById(R.id.factTypeDescrTv);
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();

            final Intent intent = new Intent(context, EditFactActivity.class);
            intent.putExtra(FactTypeIntentExtractor.FACT_TYPE_ID_EXTRA_NAME, factTypeId);

            context.startActivity(intent);
        }
    }
}
