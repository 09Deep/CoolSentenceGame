package com.coolsentencegame.interfaces;


import androidx.test.core.app.ActivityScenario;

import com.coolsentencegame.ui.MainActivity;

import junit.framework.TestCase;

public class IDatabaseTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testFetchRandomSentence() {
        try(ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            scenario.onActivity(mainActivity -> {
                //mainActivity.GetDatabase();
            });
        }
    }

    public void testStoreScore() {
    }
}