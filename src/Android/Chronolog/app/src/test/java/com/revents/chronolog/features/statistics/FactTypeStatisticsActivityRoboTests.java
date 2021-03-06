package com.revents.chronolog.features.statistics;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.RecyclerViewAdapterFactory;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.FactType;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FactTypeStatisticsActivityRoboTests extends ActivityRoboTestsBase<FactTypeStatisticsActivity> {

    private RecyclerViewAdapterFactory<FactType> mRecyclerViewAdapterFactory;
    private IntentExtractor<FactType> mFactTypeExtractor;
    private Intent mIntent;
    private FactType mFactType;

    @Before
    public void setUp() throws Exception {
        mFactType = new FactType();
        mFactTypeExtractor = mock(IntentExtractor.class);
        mIntent = new Intent();
        mRecyclerViewAdapterFactory = mock(RecyclerViewAdapterFactory.class);

        when(mFactTypeExtractor.extract(mIntent))
                .thenReturn(mFactType);

        sutBuilder = Robolectric.buildActivity(FactTypeStatisticsActivity.class);
        sut = sutBuilder.withIntent(mIntent).get();

        inject(sut);

        sutBuilder.create().start();
    }

    @Test
    public void should_set_rv_adapter_by_FactType_When_onResume() {
        // Given
        RecyclerView.Adapter expected = mock(RecyclerView.Adapter.class);
        when(mRecyclerViewAdapterFactory.create(mFactType))
                .thenReturn(expected);

        // When
        sut.onResume();

        // Then
        assertThat(((RecyclerView) viewById(R.id.statRv))
                .getAdapter())
                .isEqualTo(expected);
    }

    private void inject(final FactTypeStatisticsActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(invocation -> {

            activity.inject(mFactTypeExtractor, mRecyclerViewAdapterFactory);
            return null;
        }).when(cmp).inject(sut);
    }
}
