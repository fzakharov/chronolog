package com.revents.chronolog.features.feed;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.revents.chronolog.BuildConfig;
import com.revents.chronolog.R;
import com.revents.chronolog.app.ActivityExtractor;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.app.DateDialog;
import com.revents.chronolog.app.DateTimeProvider;
import com.revents.chronolog.app.FakeChronologApp;
import com.revents.chronolog.app.TimeDialog;
import com.revents.chronolog.db.FactWriter;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.IntentExtractor;
import com.revents.chronolog.model.Fact;
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

import java.util.Date;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = LOLLIPOP,
        application = FakeChronologApp.class)
public class EditFactActivityRoboTests extends ActivityRoboTestsBase<EditFactActivity> {

    private IntentExtractor<FactType> mExtractor;
    private Intent mIntent;
    private FactWriter mFactWriter;
    private ActivityExtractor<Fact, EditFactActivity> mFactActivityExtractor;
    private Button mUpdateBtn;
    private Button mDateBtn;
    private EditText mValueEt;
    private DateDialog mDateDialog;
    private TimeDialog mTimeDialog;
    private DateTimeProvider mDateTimeProv;
    private Button mTimeBtn;

    @Before
    public void setUp() throws Exception {

        mTimeDialog = mock(TimeDialog.class);
        mDateDialog = mock(DateDialog.class);
        mDateTimeProv = mock(DateTimeProvider.class);

        mExtractor = (IntentExtractor<FactType>) mock(IntentExtractor.class);
        mFactWriter = mock(FactWriter.class);
        mIntent = new Intent();
        mFactActivityExtractor = (ActivityExtractor<Fact, EditFactActivity>) mock(ActivityExtractor.class);

        sutBuilder = Robolectric.buildActivity(EditFactActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        CreateStart();

        mUpdateBtn = viewById(R.id.updateBtn);
        mDateBtn = viewById(R.id.dateBtn);
        mTimeBtn = viewById(R.id.timeBtn);
        mValueEt = viewById(R.id.valueEt);
    }

    @Test
    public void should_show_date_dialog_When_click_date_btn() {
        // Given

        // When
        mDateBtn.performClick();

        // Then
        verify(mDateDialog).show(sut.getFactDate(), sut, sut);
    }

    @Test
    public void should_show_time_dialog_When_click_time_btn() {
        // Given

        // When
        mTimeBtn.performClick();

        // Then
        verify(mTimeDialog).show(sut.getFactDate(), sut, sut);
    }

    @Test
    public void should_update_factDate_When_onDateChanged() {
        // Given
        Date expected = new Date(33);

        // When
        sut.onDateChanged(expected);

        // Then
        assertEquals(expected, sut.getFactDate());
    }

    @Test
    public void should_update_dateBtn_text_When_onDateChanged() {
        // Given
        Date newDate = new Date(33);
        String expected = "dd-mm-yyy";
        when(mDateTimeProv.toDateString(newDate))
                .thenReturn(expected);

        // When
        sut.onDateChanged(newDate);

        // Then
        assertEquals(expected, mDateBtn.getText());
    }

    @Test
    public void should_update_timeBtn_text_When_onDateChanged() {
        // Given
        Date newDate = new Date(33);
        String expected = "HH:MM";
        when(mDateTimeProv.toTimeString(newDate))
                .thenReturn(expected);

        // When
        sut.onDateChanged(newDate);

        // Then
        assertEquals(expected, mTimeBtn.getText());
    }

    @Test
    public void should_increase_gatFactValue_When_increaseBtn_click() {
        change_value_buttons_testCase(R.id.increaseBtn, 1);
    }

    @Test
    public void should_decrease_gatFactValue_When_decreaseBtn_click() {
        change_value_buttons_testCase(R.id.decreaseBtn, -1);
    }

    @Test
    public void should_getFactValue_return_expected_When_value_changed_in_text() {
        // Given
        Long expected = 42L;

        // When
        mValueEt.setText(expected.toString());

        // Then
        assertEquals(expected, sut.getFactValue());
    }

    @Test
    public void should_enableOrDisable_updateBtn_When_valueEt_validOrInvalid() {
        enableDisableUpdateBtnTestCase("", false);
        enableDisableUpdateBtnTestCase("q", false);
        enableDisableUpdateBtnTestCase(" ", false);
        enableDisableUpdateBtnTestCase("2.2", false);
        enableDisableUpdateBtnTestCase("42", true);
    }

    @Test
    public void should_update_extracted_fact_When_updateBtn_click() {
        // Given
        Fact expected = mock(Fact.class);
        when(mFactActivityExtractor.extract(sut))
                .thenReturn(expected);

        // When
        mUpdateBtn.performClick();

        // Then
        verify(mFactWriter).write(expected);
    }

    @Test
    public void should_set_factType_name_When_onResume() {
        // Given
        String expected = "fact type name";
        FactType factType = mock(FactType.class);

        when(factType.getName())
                .thenReturn(expected);

        when(mExtractor.extract(mIntent))
                .thenReturn(factType);

        TextView factTypeTv = viewById(R.id.factTypeTv);

        // When
        sutBuilder.resume();

        // Then
        assertEquals(expected, factTypeTv.getText());
    }

    private void change_value_buttons_testCase(@IdRes int btnId, long delta) {
        // Given
        Long expected = sut.getFactValue() + delta;

        // When
        Click(btnId);

        // Then
        assertEquals(expected, sut.getFactValue());
    }

    private void Click(@IdRes int id) {
        viewById(id).performClick();
    }

    private void enableDisableUpdateBtnTestCase(String txt, boolean isEnabled) {
        mValueEt.setText(txt);

        assertEquals(isEnabled, mUpdateBtn.isEnabled());
    }

    private void CreateStart() {
        sutBuilder.withIntent(mIntent).create().start();
    }

    private void inject(final EditFactActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(
                        mExtractor,
                        mFactWriter,
                        mFactActivityExtractor,
                        mDateDialog,
                        mTimeDialog,
                        mDateTimeProv);

                return null;
            }
        }).when(cmp).inject(sut);
    }
}
