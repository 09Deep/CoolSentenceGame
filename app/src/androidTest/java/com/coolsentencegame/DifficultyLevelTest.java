package com.coolsentencegame;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.annotation.RequiresApi;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.coolsentencegame.ui.GameSetupActivity;
import com.coolsentencegame.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class DifficultyLevelTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void difficultyTesting(){

        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));

        onView(withId(R.id.gamebutton)).perform(click());
        onView(withId(R.id.gamelevelactivity)).check(matches(isDisplayed()));

        onView(withId(R.id.radioFast)).perform(click());
        onView(withId(R.id.radioFast)).check(matches(isChecked()));
        onView(withId(R.id.radioNormal)).check(matches(isNotChecked()));

        onView(withId(R.id.btnLevel2)).perform(click());
        onView(withId(R.id.game_activity)).check(matches(isDisplayed()));
        onView(withId(R.id.btnFinish)).check(matches(withText("Finish!")));
        onView(withId(R.id.btnFinish)).perform(click());

        onView(withId(R.id.gameSummaryActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.gs_textScore)).check(matches(withText("0/0")));

        onView(withId(R.id.gs_btnHome)).perform(click());
    }
}
