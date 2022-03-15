package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.coolsentencegame.ui.LevelTwoActivity;

import org.junit.Rule;
import org.junit.Test;

public class LevelTwoActivityTest {

    @Rule
    public ActivityScenarioRule<LevelTwoActivity> levelTwoActivityActivityScenarioRule = new ActivityScenarioRule<>(LevelTwoActivity.class);

    @Test
    public void isLevelTwoActivityIsLoading(){
        onView(withId(R.id.level_two_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void isTextInActivityTwoLoading(){
        onView(withId(R.id.textView5)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonInActivityTwoLoading(){
        onView(withId(R.id.back_button_from_leveltwo)).check(matches(isDisplayed()));
    }
}