package com.coolsentencegame.objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MockDatabaseTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fetchRandomWord() {
        MockDatabase mockDatabase = new MockDatabase();

        assertNotNull(mockDatabase.FetchRandomWord());
    }

    @Test
    public void storeScore() {
    }
}