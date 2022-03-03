package com.coolsentencegame.objects;

import com.coolsentencegame.interfaces.IDatabase;

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
    private ArrayList<String> tokens;
    private int correctGuesses;
    private int wrongGuesses;

    public GameLogic(String s) {
        db = new MockDatabase();
        tokens = new ArrayList<String>();
        newSentence();
        correctGuesses = 0;
        wrongGuesses = 0;
    }

    /*
    Sets a new sentence.
     */
    public void newSentence()
    {
        prevSentence = sentence;
        while(sentence.equals(prevSentence)) {
            sentence = db.FetchRandomWord();
        }
        Collections.addAll(tokens, sentence.split(" "));
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

    public int getCorrectGuesses()
    {
        return correctGuesses;
    }

    public int getWrongGuesses()
    {
        return wrongGuesses;
    }


}
