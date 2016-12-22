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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, packageName = "com.revents.chronolog")
public class FactsfeedActivityTests {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void should_start_FactTypesActivity_When_addFactClick() {
        // Given
        FactsfeedActivity sut = Robolectric.buildActivity(FactsfeedActivity.class)
                .create()
                .start()
                .resume()
                .get();

        FloatingActionButton addBtn = (FloatingActionButton)sut.findViewById(R.id.addFactFab);

        // When
        addBtn.performClick();

        // Then
        Intent expectedIntent = new Intent(sut, FactTypesActivity.class);
        //assertThat(shadowOf(sut).getNextStartedActivity()).isEqualTo(expectedIntent);
        assertEquals(shadowOf(sut).getNextStartedActivity(), expectedIntent);
    }

}
