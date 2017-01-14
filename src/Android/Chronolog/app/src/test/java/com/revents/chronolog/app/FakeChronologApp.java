package com.revents.chronolog.app;

import android.content.Context;
import android.support.annotation.NonNull;

import static org.mockito.Mockito.mock;

public class FakeChronologApp extends ChronologApp {
    @Override
    protected AppComponent buildAppComponent() {
        return mock(AppComponent.class);
    }
}
