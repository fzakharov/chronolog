package com.revents.chronolog.features.factsfeed;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.features.facttypes.FactTypesActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP)
public class FactsfeedActivityTests {

    private FactCreator mFactCreateor;
    private FactsfeedActivity sut;

    @Before
    public void setUp() throws Exception {
       mFactCreateor = mock(FactCreator.class);

       sut = Robolectric.buildActivity(FactsfeedActivity.class)
                .create()
                .start()
                .resume()
                .get();

        sut.inject(mFactCreateor);
    }

    @Test
    public void should_call_addFact_When_addFactClick() {
        // Given
        FloatingActionButton addBtn = (FloatingActionButton)sut.findViewById(R.id.addFactFab);

        // When
        addBtn.performClick();

        // Then
        verify(mFactCreateor).addFact();
    }
}
