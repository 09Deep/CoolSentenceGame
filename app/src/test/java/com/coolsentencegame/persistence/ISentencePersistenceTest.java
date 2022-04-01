package com.coolsentencegame.persistence;

import com.coolsentencegame.application.Services;
import com.coolsentencegame.objects.Sentence;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ISentencePersistenceTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetRandomSentence() {
    }

    public void testInsertSentence() {
        SentencePersistence sentencePersistence = Services.getSentencePersistence();

        List<Sentence> sentences = new ArrayList<Sentence>();
        sentences.add(new Sentence("Why hello there", "1"));
        sentences.add(new Sentence("That is based", "2"));
        sentences.add(new Sentence("Don't have a cow man", "3"));
        sentences.add(new Sentence("Cringe bro", "4"));
        sentences.add(new Sentence("Of and words and such", "5"));
        sentences.add(new Sentence("Our table is broken", "6"));
        sentences.add(new Sentence("Very groovy", "7"));

        Sentence sentenceTest = sentencePersistence.insertSentence(sentences.get(0));

        assertEquals(sentenceTest, sentences.get(0));
    }

    public void testUpdateSentence() {
    }

    public void testDeleteSentence() {
    }
}