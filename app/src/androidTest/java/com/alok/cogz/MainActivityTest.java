package com.alok.cogz;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
    public void loadPageTest() throws Exception {
        Espresso.onView(withId(R.id.list_view));
        if (getRVcount() > 0) {
            Espresso.onView(ViewMatchers.withId(R.id.list_view)).perform(ViewActions.swipeDown());
        }

    }

    private int getRVcount() {
        RecyclerView recyclerView = activityActivityTestRule.getActivity().findViewById(R.id.list_view);
        return recyclerView.getAdapter().getItemCount();
    }

    @After
    public void tearDown() throws Exception {
    }
}