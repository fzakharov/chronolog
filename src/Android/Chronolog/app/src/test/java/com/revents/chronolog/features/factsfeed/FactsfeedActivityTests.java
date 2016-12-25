package com.revents.chronolog.features.factsfeed;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.Command;

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

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP)
public class FactsfeedActivityTests {

    private Command addFactCommand;
    private FactsfeedActivity sut;

    @Before
    public void setUp() throws Exception {
        addFactCommand = mock(Command.class);

        sut = Robolectric.buildActivity(FactsfeedActivity.class)
                .create()
                .start()
                .resume()
                .get();

        sut.inject(addFactCommand);
    }

    @Test
    public void should_setAdapter_to_factsfeedListView_When_setAdapter() {
        // Given
        ListView lvFeed = viewById(R.id.factsfeedListView);
        ArrayAdapter<String> testAdapter = new ArrayAdapter<String>(sut, 0);

        // When
        sut.setAdapter(testAdapter);

        // Then
        assertEquals(testAdapter,lvFeed.getAdapter());
    }

    @Test
    public void should_execute_addFactCommand_When_addFactFab_clicked() {
        // Given
        FloatingActionButton addBtn = viewById(R.id.addFactFab);

        // When
        addBtn.performClick();

        // Then
        verify(addFactCommand).execute();
    }

    private <T> T viewById(@IdRes int id)
    {
        return (T)sut.findViewById(id);
    }
}
