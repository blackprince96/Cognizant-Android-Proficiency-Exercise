package com.alok.cogz;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getItemCount() {
        RecyclerView recyclerView = activityActivityTestRule.getActivity().findViewById(R.id.list_view);
        int itemCount = recyclerView.getAdapter().getItemCount();
        Espresso.onView(withId(R.id.list_view)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    @Test
    public void scrollPageTest() throws Exception {
        RecyclerView recyclerView = activityActivityTestRule.getActivity().findViewById(R.id.list_view);
        int itemCount = recyclerView.getAdapter().getItemCount();

        onView(ViewMatchers.withId(R.id.list_view))
                .inRoot(RootMatchers.withDecorView(Matchers.is(activityActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));

    }

    @After
    public void tearDown() throws Exception {
    }
}