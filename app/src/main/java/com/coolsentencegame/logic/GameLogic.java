package com.coolsentencegame.logic;

import android.util.Log;

import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.persistence.MockDatabase;

import java.util.ArrayList;
import java.util.Collections;

/*
 * GameLogic
 *
 * Main logic layer class. Sets sentences and checks user guesses, and keeps track of score.
 */
public class GameLogic {
    IDatabase db;
    private String sentence;
    private String prevSentence;
    private final ArrayList<String> tokens;
    private int correctGuesses;
    private int wrongGuesses;
    private boolean answer = false;

    public GameLogic() {
        db = new MockDatabase();
        tokens = new ArrayList<String>();
        sentence = "";
        prevSentence = "";
        correctGuesses = 0;
        wrongGuesses = 0;
        newSentence();
    }

    public boolean getFlag(){

        return answer;
    }

    /*
    Sets a new sentence.
     */
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


}
