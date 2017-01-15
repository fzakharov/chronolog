package com.revents.chronolog.features.feed;

import android.content.Intent;
import android.widget.TextView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.FakeChronologApp;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.FactType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class EditFactActivityRoboTests extends ActivityRoboTestsBase<EditFactActivity> {

    private IntentExtractor<FactType> mExtractor;

    @Before
    public void setUp() throws Exception {

        mExtractor = mock(IntentExtractor.class);

        sutBuilder = Robolectric.buildActivity(EditFactActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start();
    }

    private void inject(final EditFactActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(mExtractor);
                return null;
            }
        }).when(cmp).inject(sut);
    }

    @Test
    public void should_set_factType_name_When_onResume() {
        // Given
        String expected = "fact type name";
        TextView factTypeTv = viewById(R.id.factTypeTv);
        Intent intent = mock(Intent.class);
        FactType factType = mock(FactType.class);
        when(factType.getName())
                .thenReturn(expected);

        when(mExtractor.extract(intent))
                .thenReturn(factType);

        // When
        sutBuilder.resume();

        // Then
        assertEquals(expected, factTypeTv.getText());
    }
}
