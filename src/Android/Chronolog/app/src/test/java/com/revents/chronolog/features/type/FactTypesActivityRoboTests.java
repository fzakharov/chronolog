package com.revents.chronolog.features.type;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.ResultUiCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.FakeChronologApp;
import com.revents.chronolog.db.FactReader;
import com.revents.chronolog.features.ActivityRoboTestsBase;
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

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class FactTypesActivityRoboTests extends ActivityRoboTestsBase<FactTypesActivity> {
    private ResultUiCommand<FactType> mAddFactTypeResultUiCommand;
    private FactReader mFactReader;

    @Before
    public void setUp() throws Exception {
        mFactReader = mock(FactReader.class);
        mAddFactTypeResultUiCommand = (ResultUiCommand<FactType>) mock(ResultUiCommand.class);

        sutBuilder = Robolectric.buildActivity(FactTypesActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start();
    }

    @Test
    public void should_load_fact_types_When_resume() {
        // Given
        String expectedName = "Fact Type name";
        FactType factType = new FactType(42L, expectedName, "", false, 1L, 1L);
        List<FactType> list = new ArrayList<>();
        list.add(factType);

        when(mFactReader.loadFactTypes())
                .thenReturn(list);

        // When
        sutBuilder.resume();

        RecyclerView rv = viewById(R.id.factTypesRv);
        rv.measure(0, 0);
        rv.layout(0, 0, 100, 1000);
        TextView tv = (TextView) rv.findViewHolderForLayoutPosition(0).itemView.findViewById(R.id.factTypeNameTv);

        // Then
        assertEquals(expectedName, tv.getText());
    }

    @Test
    public void should_call_addFactTypeActivityCommand_onResult_When_onActivityResult() {
        // Given
        Class<Activity> activityClass = Activity.class;
        int requestCode = 42;
        int resultCode = 33;
        Intent resultIntent = new Intent();

        sutBuilder.resume();
        sut.startActivityForResult(new Intent(sut, activityClass), requestCode);

        // When
        shadowOf(sut).receiveResult(
                new Intent(sut, activityClass),
                resultCode,
                resultIntent);

        // Then
        verify(mAddFactTypeResultUiCommand).onResult(sut, requestCode, resultCode, resultIntent);
    }

    @Test
    public void should_execute_addFactTypeCommand_When_addFactTypeFab_clicked() {
        // Given
        sutBuilder.resume();
        FloatingActionButton addBtn = viewById(R.id.addFactTypeFab);

        // When
        addBtn.performClick();

        // Then
        verify(mAddFactTypeResultUiCommand).execute(sut);
    }

    private void inject(final FactTypesActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(mAddFactTypeResultUiCommand, mFactReader);
                return null;
            }
        }).when(cmp).inject(sut);
    }
}
