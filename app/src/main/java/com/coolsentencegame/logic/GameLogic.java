package com.coolsentencegame.logic;

import android.app.Activity;
import android.util.Log;

import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.persistence.MockDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

/*
 * GameLogic
 *
 * Main logic layer class. Sets sentences and checks user guesses, and keeps track of score.
 */
public class GameLogic {
    public IDatabase db;
    private String sentence;
    private String prevSentence;
    private final ArrayList<String> tokens;
    private int correctGuesses;
    private int wrongGuesses;

    private Timer activityTimer;

    private TimerTask activityTimerTask;
    private Double time = 0.0;
    private boolean timerStarted = false;
    private boolean answer = false;
    public GameLogic() {
        db = new MockDatabase();
        tokens = new ArrayList<String>();
        sentence = "";
        prevSentence = "";
        correctGuesses = 0;
        wrongGuesses = 0;
        newSentence();

        activityTimer = new Timer();
    }

    public boolean getFlag(){

        return answer;
    }


    /*
    Sets a new sentence.
     */
//    public void newSentence()
//    {
////        tokens.clear();
////        prevSentence = sentence;
////        while(sentence.equals(prevSentence)) {
////            sentence = db.FetchRandomWord();
////        }
////        Collections.addAll(tokens, sentence.split(" "));
//    }

    public void newSentence()
    {
        tokens.clear();
        prevSentence = sentence;
        while(sentence.equals(prevSentence)) {

            sentence = db.FetchRandomWord();
            if(sentence != null) {

                Collections.addAll(tokens, sentence.split(" "));

            }
            else{
                answer=true;
                break;
            }

        }

        if(sentence == null)
            Log.v("Deep","still");

    }


    /*
     * isPlayerSentenceCorrect
     *
     * Returns a boolean that states whether a user input sentence is correct or not.
     */
    public boolean isPlayerSentenceCorrect(ArrayList<String> playerTokens) {
        boolean correct = tokens.equals(playerTokens);

        if(correct)
            correctGuesses++;
        else
            wrongGuesses++;

        return correct;
    }

    public String getSentence()
    {
        return sentence;
    }

    public ArrayList<String> getTokens()
    {
        return tokens;
    }

    public ArrayList<String> getTokensRandomized()
    {
        ArrayList<String> newTokens = new ArrayList<String>(tokens);
        Collections.shuffle(newTokens);
        return newTokens;
    }

    public int getCorrectGuesses()
    {
        return correctGuesses;
    }

    public int getWrongGuesses()
    {
        return wrongGuesses;
    }


    public void startTimer(Activity activity, Timer timer){
        Log.v("Deep","Start of timer -"+time.toString());
        activityTimerTask = new TimerTask() {
            @Override
            public void run() {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(activityTimerTask,0,1000);
    }

    public void stopTimer(){
        activityTimer.cancel();
        Log.v("Deep","Stop of timer -"+time.toString());

    }

    public void saveTime(){
        db.storeTime(time);
    }

    public String getPreviousTime(){ return getTimerText(db.getPreviousTime()); }

    public String getCurrentTime(){
        return getTimerText(db.getCurrentTime());
    }

    public double getRawTime(){
        return time;
    }

    private String getTimerText(double newTime)
    {
        Log.v("Deep","in getTimerText");
        Log.v("Deep",newTime+"");
        int rounded = (int) Math.round(newTime);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);
        Log.v("Deep",rounded+" seconds- "+seconds+" minutes- "+minutes+" hours- "+hours);
        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }

}
