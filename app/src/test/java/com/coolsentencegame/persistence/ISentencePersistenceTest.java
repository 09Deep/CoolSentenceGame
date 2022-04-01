package com.coolsentencegame.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

public class ISentencePersistenceTest {

    @Test
    public void getEasySentenceTest()
    {
        ISentencePersistence sentencePersistence = new MockSentencePersistence();
        int cutoff = sentencePersistence.getEasyHardCutoff();
        for(int i = 0; i < 20; i++) {
            assertTrue(sentencePersistence.getEasySentence().getnTokens() <= cutoff);
        }
    }

    @Test
    public void getHardSentenceTest()
    {
        ISentencePersistence sentencePersistence = new MockSentencePersistence();
        int cutoff = sentencePersistence.getEasyHardCutoff();
        for(int i = 0; i < 20; i++) {
            assertTrue(sentencePersistence.getHardSentence().getnTokens() > cutoff);
        }
    }

}