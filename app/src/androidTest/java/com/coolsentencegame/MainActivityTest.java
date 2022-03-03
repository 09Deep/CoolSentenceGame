package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;


import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void isMainActivityLoading(){
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonLoading(){
        onView(withId(R.id.gamebutton)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonText(){
        onView(withId(R.id.gamebutton)).check(matches(withText("Game")));
    }

    @Test
    public void isGameButtonWorking(){
        onView(withId(R.id.gamebutton)).perform(click());
        onView(withId(R.id.gamelevelactivity)).check(matches(isDisplayed()));
    }

}