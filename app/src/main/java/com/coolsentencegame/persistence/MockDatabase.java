package com.coolsentencegame.persistence;

import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.objects.Sentence;

import java.util.ArrayList;
import java.util.Random;

/*
 * MockDatabase
 *
 * Implements the database interface using an arraylist.
 */
public class MockDatabase implements IDatabase {

    private Random random;
    private final ArrayList<Sentence> sentences;
    private final ArrayList<Integer> scores;

    public MockDatabase() {
        random = new Random();

        //initialize phrases
        sentences = new ArrayList<Sentence>();
        sentences.add(new Sentence("Why hello there", "1"));
        sentences.add(new Sentence("That is based", "2"));
        sentences.add(new Sentence("Don't have a cow man", "3"));
        sentences.add(new Sentence("Cringe bro", "4"));
        sentences.add(new Sentence("Of and words and such", "5"));
        sentences.add(new Sentence("Our table is broken", "6"));
        sentences.add(new Sentence("Very groovy", "7"));

        scores = new ArrayList<Integer>();
    }

    /*
     * FetchRandomWord
     *
     * Returns a string at a random index within the word database's bounds.
     *
     */
    public Sentence FetchRandomSentence() {
        if(sentences.isEmpty()) {
            // TODO: THROW AN EXCEPTION
            return null;
        }
        else {
            int r = random.nextInt(sentences.size());
            return sentences.get(r);
        }
    }

    /*
     * StoreScore
     *
     * Record the current score in the database.
     */
    public void StoreScore(int score) {
        if(scores.size() == 0) {
            scores.add((score));
            return;
        }

        for(int i = 0; i < scores.size(); i++) {
            if(score > scores.get(i)) {
                scores.add(i, score);
            }
        }
    }
}
