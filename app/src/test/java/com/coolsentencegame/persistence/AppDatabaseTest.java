package com.coolsentencegame.persistence;

import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.ui.MainActivity;

import junit.framework.TestCase;

public class AppDatabaseTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testAccountDao() {
    }

    public void testSentenceDao() {
    }

    public void testFetchRandomSentence() {
        MainActivity mainActivity = new MainActivity();
        IDatabase appDatabase = mainActivity.GetDatabase();

    }

    public void testStoreScore() {
    }
}