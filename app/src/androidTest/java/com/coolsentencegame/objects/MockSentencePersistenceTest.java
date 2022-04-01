package com.coolsentencegame.objects;

import static org.junit.Assert.*;

import com.coolsentencegame.persistence.MockSentencePersistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MockSentencePersistenceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fetchRandomWord() {
        MockSentencePersistence mockSentencePersistence = new MockSentencePersistence();

//        assertNotNull(mockSentencePersistence.getRandomSentence());
    }

    @Test
    public void storeScore() {
    }
}