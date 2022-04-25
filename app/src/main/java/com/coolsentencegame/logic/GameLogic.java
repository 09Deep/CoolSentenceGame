package com.coolsentencegame.logic;

import com.coolsentencegame.objects.GameParams;
import com.coolsentencegame.objects.Score;
import com.coolsentencegame.objects.Sentence;
import com.coolsentencegame.persistence.IScorePersistence;
import com.coolsentencegame.persistence.ISentencePersistence;
import com.coolsentencegame.persistence.PersistenceException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/*
 * GameLogic
 *
 * Main logic layer class. Sets sentences and checks user guesses, and keeps track of score.
 */
public class GameLogic implements Serializable {
    private final ISentencePersistence sentencePersistence;
    private final IScorePersistence scorePersistence;
    private final GameParams gameParams;
    private Sentence curSentence;
    private Sentence prevSentence;
    private final ArrayList<String> tokens;
    private int roundsDone;
    private int correctGuesses;
    private int wrongGuesses;

    public GameLogic(GameParams gameParams, IScorePersistence scorePersistence, ISentencePersistence sentencePersistence) {
        this.sentencePersistence = sentencePersistence;
        this.scorePersistence = scorePersistence;
        this.gameParams = gameParams;
        tokens = new ArrayList<String>();
        curSentence = null;
        prevSentence = null;
        correctGuesses = 0;
        wrongGuesses = 0;
        roundsDone = 0;
        newSentence();
    }

    // Sets a new sentence.
    public void newSentence()
    {
        tokens.clear();
        prevSentence = curSentence;
        while(curSentence == null || curSentence.equals(prevSentence)) {
            // TODO: Redo all this
            curSentence = sentencePersistence.getSentence(gameParams.getMinLen(), gameParams.getMaxLen());
        }
        Collections.addAll(tokens, curSentence.toString().split(" "));
    }

    /*
     * isPlayerSentenceCorrect
     *
     * Returns a boolean that states whether a user input sentence is correct or not.
     */
    public boolean isPlayerSentenceCorrect(ArrayList<String> playerTokens) {
        boolean correct = tokens.equals(playerTokens);

        if (correct)
            correctGuesses++;
        else
            wrongGuesses++;

        roundsDone++;

        return correct;
    }

    public void finish()
    {
        try {
            scorePersistence.storeScore(new Score(correctGuesses, wrongGuesses));
        }
        catch(PersistenceException e) {
            // Handle this
            System.out.println(e.getMessage());
        }

    }

    public Sentence getSentence()
    {
        return curSentence;
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

}
