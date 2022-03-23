package com.coolsentencegame.persistence;

import android.util.Log;

import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.objects.Sentence;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * MockDatabase
 *
 * Implements the database interface using an arraylist.
 */
public class MockDatabase implements IDatabase {
    private Random random;

    private ArrayList<String> words;

    private ArrayList<Integer> scores;

    public MockDatabase() {
        random = new Random();

        //initialize phrases
        words = new ArrayList<String>();
        words.add("Why hello there");
        words.add("That is based");
        words.add("Don't have a cow man");
        words.add("Cringe bro");
        words.add("Of and words confusing a string arbitrary");
        words.add("Our table! It's broken!");
        words.add("Very groovy");

        scores = new ArrayList<Integer>();
    }

    /*
     * FetchRandomWord
     *
     * Returns a string at a random index within the word database's bounds.
     *
     */
    //changed - get to remove
    public Sentence FetchRandomSentence() {
        Sentence check = null;

        if(!words.isEmpty()){
            //check = words.remove(random.nextInt(words.size()));

        }

        return check;
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