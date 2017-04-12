package com.revents.chronolog.app;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class FakeChronologApp extends ChronologApp {

    private AppComponent mAppComponent;
    private AppCreateListener mCreateListener;

    public AppCreateListener getCreateListener(){
        return mCreateListener;
    }

    @Override
    protected AppComponent buildAppComponent() {
        mAppComponent = mock(AppComponent.class);
        mCreateListener = mock(AppCreateListener.class);

        final ChronologApp app = this;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                app.inject(mCreateListener);
                return null;
            }
        }).when(mAppComponent).inject(this);

        return mAppComponent;
    }
}
