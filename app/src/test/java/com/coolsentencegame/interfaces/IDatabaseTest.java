package com.coolsentencegame.interfaces;

import com.coolsentencegame.ui.MainActivity;

import junit.framework.TestCase;

public class IDatabaseTest extends TestCase {
    MainActivity mainActivity;
    IDatabase database;

    public void setUp() throws Exception {
        super.setUp();

        mainActivity = new MainActivity();
        database = mainActivity.GetDatabase();
    }

    public void tearDown() throws Exception {

    }

    public void testFetchRandomSentence() {
        assertTrue(database.FetchRandomSentence() != null);
    }

    public void testStoreScore() {
    }
}