package com.revents.chronolog.features.feed;

import com.revents.chronolog.app.ActivityExtractor;
import com.revents.chronolog.model.Fact;

public class EditFactActivityExtractor implements ActivityExtractor<Fact, EditFactActivity> {
    @Override
    public Fact extract(EditFactActivity editFactActivity) {
        Fact fact = new Fact();

        fact.setId(editFactActivity.getFactId());
        fact.setFactType(editFactActivity.getFactType());
        fact.setFactDate(editFactActivity.getFactDate());
        fact.setLongValue(editFactActivity.getFactValue());
        fact.setStrValue(editFactActivity.getFactDescription());

        return fact;
    }
}
