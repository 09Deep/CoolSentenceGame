package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.coolsentencegame.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class MenuNavTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void isMainActivityLoading(){
        // Check that we can navigate to and from our menus

        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));

        onView(withId(R.id.btnStats)).perform(click());
        onView(withId(R.id.statsActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_back_stats)).perform(click());

        onView(withId(R.id.gamebutton)).perform(click());
        onView(withId(R.id.gamelevelactivity)).check(matches(isDisplayed()));
        onView(withId(R.id.back_button_from_gamelevels)).perform(click());

        onView(withId(R.id.btnsetting)).perform(click());
        onView(withId(R.id.activitySettings)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_settings_back)).perform(click());

        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }
}