package com.revents.chronolog.features;

import android.app.Activity;

import com.revents.chronolog.app.ActivityCommand;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.FactEditor;
import com.revents.chronolog.model.Fact;

public class NewFactActivityCommand implements ActivityCommand {

    private FactWriter factWriter;
    private FactEditor factEditor;

    public NewFactActivityCommand(FactWriter factWriter, FactEditor factEditor) {
        this.factWriter = factWriter;
        this.factEditor = factEditor;
    }

    @Override
    public void execute(Activity current) {
        Fact fact = factEditor.newFact(current);
        factWriter.write(fact);
    }
}
