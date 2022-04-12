package com.coolsentencegame.integration;

import static org.junit.Assert.*;

import com.coolsentencegame.application.Services;
import com.coolsentencegame.logic.GameLogic;
import com.coolsentencegame.objects.GameParams;
import com.coolsentencegame.objects.Score;
import com.coolsentencegame.persistence.IScorePersistence;
import com.coolsentencegame.persistence.MockScorePersistence;
import com.coolsentencegame.persistence.MockSentencePersistence;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class GameLogicScoreTest {

    @Test
    public void automatedTest()
    {
        IScorePersistence scorePersistence = new MockScorePersistence();
        Random rand = new Random();
        ArrayList<String> wrongTokens = new ArrayList<>();
        int startSize = scorePersistence.getPrevScores(0).size();
        GameParams gameParams = new GameParams(GameParams.Speed.NORMAL, 1, 99);

        for(int i = 0; i < 20; i++) {
            int correct = 1 + rand.nextInt(5);
            int wrong = 1 + rand.nextInt(5);
            GameLogic gameLogic = new GameLogic(gameParams, scorePersistence, new MockSentencePersistence());
            ArrayList<String> tokens = gameLogic.getTokens();

            // Make some right guesses
            for(int j = 0; j < correct; j++) {
                assertTrue(gameLogic.isPlayerSentenceCorrect(tokens));
            }

            // Make some wrong guesses
            for(int j = 0; j < wrong; j++) {
                assertFalse(gameLogic.isPlayerSentenceCorrect(wrongTokens));
            }

            gameLogic.finish();

            // Check that it was added
            ArrayList<Score> scores = scorePersistence.getPrevScores(1);
            assertEquals(1, scores.size());
            Score theScore = scores.get(0);
            assertEquals(correct, theScore.getCorrect());
            assertEquals(wrong, theScore.getWrong());
            assertEquals(correct+wrong, theScore.getTotal());

            // Cleanup
            scorePersistence.removeScore(theScore);
        }

        int endSize = scorePersistence.getPrevScores(0).size();
        assertEquals(startSize, endSize);

    }

}
