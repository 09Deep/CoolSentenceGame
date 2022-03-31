package com.coolsentencegame.persistence;

import android.util.Log;

import com.coolsentencegame.interfaces.IDatabase;

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

    private ArrayList<Double> time;

    public MockDatabase() {
        Log.v("Deep", "new mockdatabase is created");
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

        time = new ArrayList<Double>(2);
    }

    /*
     * FetchRandomWord
     *
     * Returns a string at a random index within the word database's bounds.
     *
     */
    public String FetchRandomWord() {
        String check;

        if(!words.isEmpty()){
            check = words.remove(random.nextInt(words.size()));
        }
        else{
            check=null;
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

    public void storeTime(double addTime){
        int timeListSize = time.size();

        if(timeListSize == 0 || timeListSize == 1){
            Log.v("Deep","In database 1");
            Log.v("Deep","Size of the database "+timeListSize);
            Log.v("Deep",addTime+"");
            time.add(addTime);
            Log.v("Deep","Size of the database "+time.size());
            Log.v("Deep","at 1st index "+time.get(0));

        }
        else{
            time.remove(0);
            time.add(addTime);
            Log.v("Deep","In database 2");
            Log.v("Deep",addTime+"");
        }
    }

    public double getPreviousTime(){
        Log.v("Deep","getPreviousTime is called");

        if(time.size() > 1) {
            return time.get(0);
        }
        return -1;
    }

    public double getCurrentTime(){
        Log.v("Deep","getCurrentTime is called");
        Log.v("Deep","Size of the database "+time.size());
        if(time.size() == 1){
            return time.get(0);
        }
        if(time.size() == 2){
            return time.get(1);
        }
        return -1;
    }

}
