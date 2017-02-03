package com.revents.chronolog.features.feed;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.widget.TextView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.EventArgs;
import com.revents.chronolog.app.EventListener;
import com.revents.chronolog.app.FakeChronologApp;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.app.YesNoDialog;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.model.Fact;
import com.revents.chronolog.model.FactType;
import com.revents.chronolog.model.ValueDescriptor;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class FactsfeedActivityRoboTests {

    private ResultUiCommand addFactResultUiCommand;
    private FactsfeedActivity sut;
    private ActivityController<FactsfeedActivity> sutBuilder;
    private FactReader mFactReader;
    private RecyclerView mfactsFeedRv;
    private YesNoDialog mYesNoDialog;
    private FactWriter mFactWriter;

    @Before
    public void setUp() throws Exception {
        addFactResultUiCommand = mock(ResultUiCommand.class);
        mFactWriter = mock(FactWriter.class);
        mFactReader = mock(FactReader.class);
        mYesNoDialog = mock(YesNoDialog.class);

        sutBuilder = Robolectric.buildActivity(FactsfeedActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start();

        mfactsFeedRv = viewById(R.id.factsfeedRv);
    }

    private void inject(final FactsfeedActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(addFactResultUiCommand, mFactReader, mFactWriter, mYesNoDialog);
                return null;
            }
        }).when(cmp).inject(sut);
    }

    @Test
    public void should_dialog_yes_no_When_long_click_on_item() {
        // Given
        String expectedName = "coffee";
        Fact fact = createTestFact(expectedName);

        List<Fact> list = new ArrayList<>();
        list.add(fact);

        when(mFactReader.loadFactsfeed())
                .thenReturn(list);

        sutBuilder.resume();

        // When
        mfactsFeedRv.measure(0, 0);
        mfactsFeedRv.layout(0, 0, 100, 1000);
        mfactsFeedRv.findViewHolderForLayoutPosition(0).itemView.performLongClick();

        // Then
        verify(mYesNoDialog).show(eq(sut), eq(fact), isNotNull(String.class), eq(sut));
    }

    @Test
    public void should_delete_Fact_When_onEvent() {
        // Given
        Fact fact = createTestFact("any name");

        // When
        sut.onEvent(new EventArgs<>(new Pair<>(true, fact)));

        // Then
        verify(mFactWriter).delete(fact);
    }

    @Test
    public void should_load_facts_When_resume() {
        // Given
        String expectedName = "coffee";
        Fact fact = createTestFact(expectedName);

        List<Fact> list = new ArrayList<>();
        list.add(fact);

        when(mFactReader.loadFactsfeed())
                .thenReturn(list);

        // When
        sutBuilder.resume();

        mfactsFeedRv.measure(0, 0);
        mfactsFeedRv.layout(0, 0, 100, 1000);
        TextView tv = (TextView) mfactsFeedRv.findViewHolderForLayoutPosition(0).itemView.findViewById(R.id.headerTv);

        // Then
        assertEquals(expectedName, tv.getText());
    }

    @NonNull
    private Fact createTestFact(String expectedTypeName) {
        Fact fact = new Fact(42L, null, new Date(), 1L, "", 1L);
        FactType factType = new FactType(1L, expectedTypeName, "", false, 1L, 1L);
        factType.setValueDescriptor(new ValueDescriptor(1L, "", "", "default", ""));
        fact.setFactType(factType);
        return fact;
    }

    @Test
    public void should_execute_addFactCommand_When_addFactFab_clicked() {
        // Given
        FloatingActionButton addBtn = viewById(R.id.addFactFab);

        // When
        addBtn.performClick();

        // Then
        verify(addFactResultUiCommand).execute(sut);
    }

    private <T> T viewById(@IdRes int id) {
        return (T) sut.findViewById(id);
    }
}
