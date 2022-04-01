package com.coolsentencegame.logic;

import com.coolsentencegame.application.Services;
import com.coolsentencegame.objects.Sentence;
import com.coolsentencegame.persistence.IScorePersistence;
import com.coolsentencegame.persistence.ISentencePersistence;
import com.coolsentencegame.persistence.MockSentencePersistence;

import java.util.ArrayList;
import java.util.Collections;

/*
 * GameLogic
 *
 * Main logic layer class. Sets sentences and checks user guesses, and keeps track of score.
 */
public class GameLogic {
    private final ISentencePersistence sentencePersistence;
    private final IScorePersistence scorePersistence;
    private Sentence sentence;
    private Sentence prevSentence;
    private final ArrayList<String> tokens;
    private int nRounds;
    private int roundsDone;
    private int correctGuesses;
    private int wrongGuesses;

    public GameLogic() {
        sentencePersistence = new MockSentencePersistence();
        scorePersistence = Services.getScorePersistence();
        tokens = new ArrayList<String>();
        sentence = sentencePersistence.getRandomSentence();
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
            sentence = sentencePersistence.getRandomSentence();
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

        if(isDone())
            scorePersistence.StoreScore(correctGuesses, wrongGuesses);

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
