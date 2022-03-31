package com.coolsentencegame.logic;

import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.objects.Sentence;
import com.coolsentencegame.persistence.MockDatabase;

import java.util.ArrayList;
import java.util.Collections;

/*
 * GameLogic
 *
 * Main logic layer class. Sets sentences and checks user guesses, and keeps track of score.
 */
public class GameLogic {
    private IDatabase database;
    private Sentence sentence;
    private Sentence prevSentence;
    private final ArrayList<String> tokens;
    private int nRounds;
    private int roundsDone;
    private int correctGuesses;
    private int wrongGuesses;

    public GameLogic() {
        database = new MockDatabase();
        tokens = new ArrayList<String>();
        sentence = database.FetchRandomSentence();
        prevSentence = null;
        correctGuesses = 0;
        wrongGuesses = 0;
        roundsDone = 0;
    }

    public boolean isDone()
    {
        return roundsDone >= nRounds;
    }

    public void setNumberRounds(int nRounds)
    {
        this.nRounds = nRounds;
    }

    /*
    Sets a new sentence.
     */
    public void newSentence()
    {
        tokens.clear();
        prevSentence = sentence;
        while(sentence.equals(prevSentence)) {
            sentence = database.FetchRandomSentence();
        }
        Collections.addAll(tokens, sentence.getSentence().split(" "));
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

        roundsDone++;
        return correct;
    }

    public Sentence getSentence()
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

    public int getCurrentRoundNumber()
    {
        return roundsDone;
    }

    public int getNumberRounds()
    {
        return nRounds;
    }

}
