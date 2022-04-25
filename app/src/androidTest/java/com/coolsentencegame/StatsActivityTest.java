package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.coolsentencegame.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class StatsActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void isStatsAvtivityLoading(){
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));

        onView(withId(R.id.btnStats)).perform(click());
        onView(withId(R.id.statsActivity)).check(matches(isDisplayed()));

        onView(withId(R.id.btn_back_stats)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }
}
