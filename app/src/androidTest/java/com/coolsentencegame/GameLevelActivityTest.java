package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class GameLevelActivityTest {

    @Rule
    public ActivityScenarioRule<GameLevelActivity> gameLevelActivityActivityScenarioRule = new ActivityScenarioRule<>(GameLevelActivity.class);

    @Test
    public void isGameLevelActivityLoading(){
        onView(withId(R.id.gamelevelactivity)).check(matches(isDisplayed()));
    }

    @Test
    public void isTextVisible(){
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonVisible(){
        onView(withId(R.id.buttontolevel1)).check(matches(isDisplayed()));
        onView(withId(R.id.buttontolevel2)).check(matches(isDisplayed()));
        onView(withId(R.id.buttontolevel3)).check(matches(isDisplayed()));
        onView(withId(R.id.back_button_from_gamelevels)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonTextVisible(){
        onView(withId(R.id.buttontolevel1)).check(matches(withText("Level 1")));
        onView(withId(R.id.buttontolevel2)).check(matches(withText("Level 2")));
        onView(withId(R.id.buttontolevel3)).check(matches(withText("Level 3")));
    }
}