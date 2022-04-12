package com.coolsentencegame.persistence;

import static org.junit.Assert.*;

import com.coolsentencegame.objects.Sentence;

import org.junit.Test;

public class ISentencePersistenceTest {

    @Test
    public void getSentenceTest()
    {
        ISentencePersistence sentencePersistence = new MockSentencePersistence();

        for(int val = 1; val < 5; val++) {
            for(int i = 3; i < 7; i++) {
                Sentence s = sentencePersistence.getSentence(val, val+2);
                assertTrue(s.getnTokens() >= val);
                assertTrue(s.getnTokens() <= val+2);
            }
        }
    }

}