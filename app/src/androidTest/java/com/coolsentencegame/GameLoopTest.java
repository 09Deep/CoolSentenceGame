package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.annotation.ContentView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.coolsentencegame.ui.MainActivity;
import com.coolsentencegame.ui.StatsActivity;

import org.junit.Rule;
import org.junit.Test;

public class GameLoopTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void gameLoopTest()
    {
        // Check that we can start a round, play the round, and finish the round
        // Also check that we can see the score after the game

        onView(withId(R.id.gamebutton)).perform(click());
        onView(withId(R.id.gamelevelactivity)).check(matches(isDisplayed()));

        onView(withId(R.id.btnLevel1)).perform(click());
        onView(withId(R.id.game_activity)).check(matches(isDisplayed()));

        onView(withId(R.id.btnStart)).perform(click());
        onView(withId(R.id.btnCheck)).perform(click());

        onView(withText(R.string.snackbar_message)).check(matches(isDisplayed()));

        onView(withId(R.id.btnFinish)).perform(click());

        onView(withId(R.id.gameSummaryActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.textView5)).check(matches(withText("Game Done")));
        onView(withId(R.id.gs_textScore)).check(matches(withText("0/1")));

        onView(withId(R.id.gs_btnHome)).perform(click());
    }

}
