package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class LevelOneActivityTest {

    @Rule
    public ActivityScenarioRule<LevelOneActivity> levelOneActivityActivityScenarioRule = new ActivityScenarioRule<>(LevelOneActivity.class);

    @Test
    public void isLevelOneActivityLoading(){
        onView(withId(R.id.level_one_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void isTextInActivityOneLoading(){
        onView(withId(R.id.textView4)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonInActivityOneLoading(){
        onView(withId(R.id.back_button_from_levelone)).check(matches(isDisplayed()));
    }
}