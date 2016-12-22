package com.revents.chronolog.features.factsfeed;

import android.widget.ListView;
import android.widget.RelativeLayout;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.FactsViewActivity;
import com.revents.chronolog.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, packageName = "com.revents.chronolog")
public class FactsfeedActivityTests {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void should_start_factTypes_selector_activity_When_addFactClick() {
        // Given
        FactsfeedActivity sut = Robolectric.buildActivity(FactsfeedActivity.class)
                .create()
                .start()
                .resume()
                .get();


        // When
        sut.addFactClick(null);

        // Then
    }

}
