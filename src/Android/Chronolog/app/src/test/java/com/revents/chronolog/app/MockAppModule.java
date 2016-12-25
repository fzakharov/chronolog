package com.revents.chronolog.app;

import android.content.Context;

import com.revents.chronolog.db.FactWriter;

public class MockAppModule extends AppModule
{
    public MockAppModule(Context context) {
        super(context);
    }

    @Override
    public Command provideWriteFactCommand(FactWriter factWriter, FactBuilder factBuilder) {
        return super.provideWriteFactCommand(factWriter, factBuilder);
    }
}
