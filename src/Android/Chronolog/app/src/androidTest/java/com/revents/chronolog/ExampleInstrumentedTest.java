package com.revents.chronolog;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.revents.chronolog.features.feed.FactsfeedActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<FactsfeedActivity> mActivityRule = new ActivityTestRule<>(
            FactsfeedActivity.class);

    @Test
    public void should_add_fact() throws Exception {

        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.factsfeedRv);
        int expectedCount = recyclerView.getAdapter().getItemCount() + 1;

        Click(R.id.addFactFab);

        Espresso.onView(ViewMatchers.withId(R.id.factTypesRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Click(R.id.increaseBtn);
        Click(R.id.increaseBtn);
        Click(R.id.increaseBtn);
        Click(R.id.updateBtn);

        Espresso.pressBack();

        Espresso.onView(ViewMatchers.withId(R.id.factsfeedRv))
                .check(new RecyclerViewItemCountAssertion(expectedCount));
    }

    @Test
    public void should_select_group() throws Exception {

        Click(R.id.addFactFab);
        Click(R.id.addFactTypeFab);
        Click(R.id.selectGroupBtn);
        Click(R.id.addFactTypeGroupFab);
    }

    @Test
    public void should_select_value_type() throws Exception {

        Click(R.id.addFactFab);
        Click(R.id.addFactTypeFab);
        Click(R.id.selectValueDescriptorBtn);
        Click(R.id.addValueDescriptorFab);
    }

    private void Click(final int id) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click());
    }
}
