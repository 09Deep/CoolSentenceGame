package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.coolsentencegame.ui.GameSetupActivity;

import org.junit.Rule;
import org.junit.Test;

public class GameSetupActivityTest {

    @Rule
    public ActivityScenarioRule<GameSetupActivity> gameLevelActivityActivityScenarioRule = new ActivityScenarioRule<>(GameSetupActivity.class);

    @Test
    public void isGameLevelActivityLoading(){
        onView(withId(R.id.gamelevelactivity)).check(matches(isDisplayed()));
    }

    @Test
    public void isTextVisible(){
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
        onView(withId(R.id.gs_textTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonVisible(){
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()));
        onView(withId(R.id.back_button_from_gamelevels)).check(matches(isDisplayed()));
    }

    @Test
    public void isButtonTextVisible(){
        onView(withId(R.id.btn_start)).check(matches(withText("Level 1")));
    }

    @Test
    public void isLevelOneButtonWorking(){
        onView(withId(R.id.btn_start)).perform(click());
        onView(withId(R.id.game_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void isBackButtonFromGameLevelWorking(){
        onView(withId(R.id.back_button_from_gamelevels)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }

}