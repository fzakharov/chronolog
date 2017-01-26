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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
    private EditText mValueEt;
    private DateDialog mDateDialog;
    private TimeDialog mTimeDialog;

    @Before
    public void setUp() throws Exception {

        mTimeDialog = mock(TimeDialog.class);
        mDateDialog = mock(DateDialog.class);
        mExtractor = (IntentExtractor<FactType>) mock(IntentExtractor.class);
        mFactWriter = mock(FactWriter.class);
        mIntent = new Intent();
        mFactActivityExtractor = (ActivityExtractor<Fact, EditFactActivity>) mock(ActivityExtractor.class);

        sutBuilder = Robolectric.buildActivity(EditFactActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        CreateStart();

        mUpdateBtn = viewById(R.id.updateBtn);
        mValueEt = viewById(R.id.valueEt);
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

                activity.inject(mExtractor, mFactWriter, mFactActivityExtractor, mDateDialog, mTimeDialog);
                return null;
            }
        }).when(cmp).inject(sut);
    }

    @Test
    public void should_change_date_through_date_dialog_When_dateBtn_click() {

        dateDialogTestCase(new Date(33), new Date(33), true);
    }

    @Test
    public void should_not_change_date_through_date_dialog_When_dateBtn_click() {

        dateDialogTestCase(sut.getFactDate(), new Date(33), false);
    }

    @Test
    public void should_change_time_through_time_dialog_When_timeBtn_click() {

        timeDialogTestCase(new Date(33), new Date(33), true);
    }

    @Test
    public void should_not_change_time_through_time_dialog_When_timeBtn_click() {

        timeDialogTestCase(sut.getFactDate(), new Date(33), false);
    }

    @Test
    public void should_increase_gatFactValue_When_increaseBtn_click() {
        // Given
        Long expected = sut.getFactValue() + 1;

        // When
        Click(R.id.increaseBtn);

        // Then
        assertEquals(expected, sut.getFactValue());
    }

    @Test
    public void should_decrease_gatFactValue_When_decreaseBtn_click() {
        // Given
        Long expected = sut.getFactValue() - 1;

        // When
        Click(R.id.decreaseBtn);

        // Then
        assertEquals(expected, sut.getFactValue());
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

    private void dateDialogTestCase(Date expected, Date returnDate, boolean showResult){
        // Given
        when(mDateDialog.Show(sut.getFactDate()))
                .thenReturn(showResult);

        when(mDateDialog.getSelectedDate())
                .thenReturn(returnDate);

        // When
        Click(R.id.dateBtn);

        // Then
        assertEquals(expected, sut.getFactDate());
    }

    private void timeDialogTestCase(Date expected, Date returnDate, boolean showResult){
        // Given
        when(mTimeDialog.Show(sut.getFactDate()))
                .thenReturn(showResult);

        when(mTimeDialog.getSelectedTime())
                .thenReturn(returnDate);

        // When
        Click(R.id.timeBtn);

        // Then
        assertEquals(expected, sut.getFactDate());
    }

    private void Click(@IdRes int id) {
        viewById(id).performClick();
    }

    private void enableDisableUpdateBtnTestCase(String txt, boolean isEnabled) {
        mValueEt.setText(txt);

        assertEquals(isEnabled, mUpdateBtn.isEnabled());
    }
}
