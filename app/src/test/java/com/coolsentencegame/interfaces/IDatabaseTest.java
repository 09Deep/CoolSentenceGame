package com.coolsentencegame.interfaces;


import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.coolsentencegame.ui.MainActivity;

import junit.framework.TestCase;

import org.junit.Rule;
import org.robolectric.Robolectric;

public class IDatabaseTest extends TestCase {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testFetchRandomSentence() {
        try(ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            scenario.onActivity(mainActivity -> {
                mainActivity.GetDatabase();
            });
        }
    }

    public void testStoreScore() {
    }
}