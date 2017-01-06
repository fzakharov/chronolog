package com.revents.chronolog.features.type;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.revents.chronolog.R;
import com.revents.chronolog.app.UiCommand;
import com.revents.chronolog.app.AppComponent;
import com.revents.chronolog.app.ChronologApp;
import com.revents.chronolog.features.ActivityRoboTestsBase;
import com.revents.chronolog.features.type.EditFactTypeActivity;
import com.revents.chronolog.model.FactTypeGroup;
import com.revents.chronolog.model.ValueDescriptor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

public class EditFactTypeActivityRoboTests extends ActivityRoboTestsBase<EditFactTypeActivity> {

    private UiCommand<FactTypeGroup> mSelectFactTypeGroupCommand;
    private UiCommand<ValueDescriptor> mSelectValueDescriptorCommand;

    @Before
    public void setUp() throws Exception {
        mSelectFactTypeGroupCommand = (UiCommand<FactTypeGroup>) mock(UiCommand.class);
        mSelectValueDescriptorCommand = (UiCommand<ValueDescriptor>) mock(UiCommand.class);

        sutBuilder = Robolectric.buildActivity(EditFactTypeActivity.class);
        sut = sutBuilder.get();

        inject(sut);

        sutBuilder.create().start().resume();
    }

    @Test
    public void should_execute_select_group_When_selectGroupBtn_clicked() {

        should_execute_When_click_test_case(R.id.selectGroupBtn, mSelectFactTypeGroupCommand);
    }

    @Test
    public void should_execute_select_value_descriptor_When_selectValueDescriptorBtn_clicked() {

        should_execute_When_click_test_case(R.id.selectValueDescriptorBtn, mSelectValueDescriptorCommand);
    }

    @Test
    public void should_update_group_name_tv_When_onActivityResult() {
        // Given
        Class<Activity> activityClass = Activity.class;
        int requestCode = 42;
        int resultCode = 33;
        Intent resultIntent = new Intent();

        sut.startActivityForResult(new Intent(sut, activityClass), requestCode);
        String expectedName = "Group Name";
        FactTypeGroup ftg = new FactTypeGroup(1l, expectedName, "", 0);
        when(mSelectFactTypeGroupCommand.onResult(sut, requestCode, resultCode, resultIntent))
                .thenReturn(ftg);

        // When
        shadowOf(sut).receiveResult(
                new Intent(sut, activityClass),
                resultCode,
                resultIntent);

        // Then
        TextView tv = ((TextView) viewById(R.id.groupTv));
        assertEquals(expectedName, tv.getText());
    }

    private void inject(final EditFactTypeActivity activity) {
        ChronologApp app = (ChronologApp) RuntimeEnvironment.application;
        AppComponent cmp = app.getAppComponent();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                activity.inject(mSelectFactTypeGroupCommand, mSelectValueDescriptorCommand);
                return null;
            }
        }).when(cmp).inject(sut);
    }
}
