package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Sentence;

import java.util.ArrayList;
import java.util.Random;

/*
 * MockDatabase
 *
 * Implements the database interface using an arraylist.
 */
public class MockSentencePersistence implements ISentencePersistence {

    private Random random;
    private final ArrayList<Sentence> sentences;

    public MockSentencePersistence() {
        random = new Random();

        //initialize phrases
        sentences = new ArrayList<Sentence>();
        sentences.add(new Sentence("Why hello there", 1));
        sentences.add(new Sentence("That is based", 2));
        sentences.add(new Sentence("Don't have a cow man", 3));
        sentences.add(new Sentence("Cringe bro", 4));
        sentences.add(new Sentence("Of and words and such", 5));
        sentences.add(new Sentence("Our table is broken", 6));
        sentences.add(new Sentence("Very groovy", 7));
    }

    @Override
    public Sentence getRandomSentence() {
        if(sentences.isEmpty()) {
            // TODO: THROW AN EXCEPTION
            return null;
        }
        else {
            int r = random.nextInt(sentences.size());
            return sentences.get(r);
        }
    }

    @Override
    public Sentence insertSentence(Sentence sentence) {
        return null;
    }

    @Override
    public Sentence updateSentence(Sentence sentence) {
        return null;
    }

    @Override
    public void deleteSentence(Sentence sentence) {

    }
}
