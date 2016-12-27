package com.revents.chronolog.features;

import android.app.Activity;

import com.revents.chronolog.model.Fact;

public interface FactEditor {
    Fact newFact(Activity current);
}
