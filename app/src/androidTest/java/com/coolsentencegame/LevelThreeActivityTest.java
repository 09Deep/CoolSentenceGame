package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class LevelThreeActivityTest {

    @Rule
    public ActivityScenarioRule<LevelThreeActivity> levelThreeActivityActivityScenarioRule = new ActivityScenarioRule<>(LevelThreeActivity.class);

    @Test
    public void isLevelThreeActivityLoading(){
        onView(withId(R.id.level_three_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void isTextInLevelThreeActivityLoading(){
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonInLevelThreeActivityLoading(){

    }
}