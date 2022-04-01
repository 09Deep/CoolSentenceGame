package com.coolsentencegame.logic;

import com.coolsentencegame.application.Services;
import com.coolsentencegame.objects.Score;
import com.coolsentencegame.objects.Sentence;
import com.coolsentencegame.persistence.IScorePersistence;
import com.coolsentencegame.persistence.ISentencePersistence;
import com.coolsentencegame.persistence.MockSentencePersistence;
import com.coolsentencegame.persistence.PersistenceException;

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
    private final int nRounds;
    private int roundsDone;
    private int correctGuesses;
    private int wrongGuesses;
    private GameLogic.Difficulty difficulty;

    public enum Difficulty {
        EASY,
        HARD
    }

    public GameLogic(int nRounds, IScorePersistence scorePersistence)
    {
        this(nRounds, Difficulty.EASY, scorePersistence);
    }

    public GameLogic(int nRounds, GameLogic.Difficulty difficulty, IScorePersistence scorePersistence) {
        sentencePersistence = new MockSentencePersistence();
        this.scorePersistence = scorePersistence;
        tokens = new ArrayList<String>();
        sentence = null;
        prevSentence = null;
        correctGuesses = 0;
        wrongGuesses = 0;
        roundsDone = 0;
        this.nRounds = nRounds;
        newSentence();
        this.difficulty = difficulty;
    }

    public boolean isDone()
    {
        return roundsDone >= nRounds;
    }

    /*
    Sets a new sentence.
     */
    public void newSentence()
    {
        tokens.clear();
        prevSentence = sentence;
        while(sentence == null || sentence.equals(prevSentence)) {
            if(difficulty == Difficulty.EASY)
                sentence = sentencePersistence.getEasySentence();
            else
                sentence = sentencePersistence.getHardSentence();
        }
        Collections.addAll(tokens, sentence.toString().split(" "));
    }

    /*
     * isPlayerSentenceCorrect
     *
     * Returns a boolean that states whether a user input sentence is correct or not.
     */
    public boolean isPlayerSentenceCorrect(ArrayList<String> playerTokens) {
        boolean correct = tokens.equals(playerTokens);

        if(!isDone()) {
            if (correct)
                correctGuesses++;
            else
                wrongGuesses++;

            roundsDone++;
            if(isDone()){
                try {
                    scorePersistence.StoreScore(new Score(correctGuesses, wrongGuesses));
                }
                catch(PersistenceException e) {
                    // Handle this
                    System.out.println(e.getMessage());
                }
            }

        }

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
