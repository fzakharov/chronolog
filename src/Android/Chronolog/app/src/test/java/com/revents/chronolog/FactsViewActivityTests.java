package com.revents.chronolog;

import android.database.sqlite.SQLiteDatabase;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, packageName = "com.revents.chronolog")
public class FactsViewActivityTests {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void should_fill_list_When_start_activity() {
        // Given
        FactsViewActivity sut = Robolectric.buildActivity(FactsViewActivity.class)
                .create()
                .start()
                .get();

        // When
        // Then
        RelativeLayout frame = (RelativeLayout) sut.findViewById(R.id.content_facts_view);
        ListView list = (ListView) frame.findViewById(R.id.factsList);

        assertEquals(1, list.getAdapter().getCount());

    }

}
