package com.revents.chronolog.app;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class FakeChronologApp extends ChronologApp {

    private AppComponent mAppComponent;

    @Override
    protected AppComponent buildAppComponent() {
        mAppComponent = mock(AppComponent.class);
        final ChronologApp app = this;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                // TODO: 20.01.2017 java.lang.StackOverflowError
                mAppComponent.inject(app);
                return null;
            }
        }).when(mAppComponent).inject(this);

        return mAppComponent;
    }
}
