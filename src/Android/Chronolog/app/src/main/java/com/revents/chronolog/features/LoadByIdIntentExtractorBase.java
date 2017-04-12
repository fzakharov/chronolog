package com.revents.chronolog.features;

import android.content.Intent;

import com.revents.chronolog.db.FactReader;

public abstract class LoadByIdIntentExtractorBase<T> implements IntentExtractor<T> {
    private String mIdExtraName;
    private FactReader mFactReader;

    protected LoadByIdIntentExtractorBase(String idExtraName, FactReader factReader) {

        mIdExtraName = idExtraName;
        mFactReader = factReader;
    }

    protected abstract T loadFrom(FactReader factReader, long id);

    @Override
    public T extract(Intent data) {
        long id = data.getLongExtra(mIdExtraName, -1);
        if (id == -1)
            return null;

        return loadFrom(mFactReader, id);
    }
}
