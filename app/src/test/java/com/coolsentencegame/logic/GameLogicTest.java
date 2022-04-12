package com.coolsentencegame.logic;

import static org.junit.Assert.*;

import com.coolsentencegame.objects.GameParams;
import com.coolsentencegame.objects.Sentence;
import com.coolsentencegame.persistence.MockScorePersistence;
import com.coolsentencegame.persistence.MockSentencePersistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogicTest {

    @Test
    public void newSentence() {
        GameParams gameParams = new GameParams(GameParams.Speed.NORMAL, 1, 3);
        GameLogic gameLogic = new GameLogic(gameParams, new MockScorePersistence(), new MockSentencePersistence());
        Sentence sOne, sTwo;

        gameLogic.newSentence();
        sOne = gameLogic.getSentence();

        gameLogic.newSentence();
        sTwo = gameLogic.getSentence();

        assertNotEquals(sOne.toString(), sTwo.toString());
    }

    @Test
    public void isPlayerSentenceCorrect() {
        GameParams gameParams = new GameParams(GameParams.Speed.NORMAL, 1, 3);
        GameLogic gameLogic = new GameLogic(gameParams, new MockScorePersistence(), new MockSentencePersistence());
        String sentence;

        gameLogic.newSentence();
        sentence = gameLogic.getSentence().toString();

        assertTrue(gameLogic.isPlayerSentenceCorrect(new ArrayList<String>(Arrays.asList(sentence.split(" ")))));
    }

    @Test
    public void getSentence() {
        GameParams gameParams = new GameParams(GameParams.Speed.NORMAL, 1, 3);
        GameLogic gameLogic = new GameLogic(gameParams, new MockScorePersistence(), new MockSentencePersistence());

        gameLogic.newSentence();
        assertNotNull(gameLogic.getSentence());
    }

    @Test
    public void getTokens() {
        GameParams gameParams = new GameParams(GameParams.Speed.NORMAL, 1, 3);
        GameLogic gameLogic = new GameLogic(gameParams, new MockScorePersistence(), new MockSentencePersistence());
        ArrayList<String> tokens;

        gameLogic.newSentence();
        tokens = gameLogic.getTokens();

        assertNotNull(tokens);
    }

    @Test
    public void getCorrectGuesses() {
        GameParams gameParams = new GameParams(GameParams.Speed.NORMAL, 1, 9);
        GameLogic gameLogic = new GameLogic(gameParams, new MockScorePersistence(), new MockSentencePersistence());

        // 0 Start
        assertEquals(0, gameLogic.getCorrectGuesses());

        // A correct guess
        ArrayList<String> s1 = gameLogic.getTokens();
        assertTrue(gameLogic.isPlayerSentenceCorrect(s1));
        assertEquals(1, gameLogic.getCorrectGuesses());

        // A wrong guess
        ArrayList<String> s2 = new ArrayList<>();
        assertFalse(gameLogic.isPlayerSentenceCorrect(s2));
        assertEquals(1, gameLogic.getCorrectGuesses());

    }

    @Test
    public void getWrongGuesses() {
        GameParams gameParams = new GameParams(GameParams.Speed.NORMAL, 1, 3);
        GameLogic gameLogic = new GameLogic(gameParams, new MockScorePersistence(), new MockSentencePersistence());

        // 0 Start
        assertEquals(0, gameLogic.getWrongGuesses());

        // A correct guess
        ArrayList<String> s1 = gameLogic.getTokens();
        assertTrue(gameLogic.isPlayerSentenceCorrect(s1));
        assertEquals(0, gameLogic.getWrongGuesses());

        // A wrong guess
        ArrayList<String> s2 = new ArrayList<>();
        assertFalse(gameLogic.isPlayerSentenceCorrect(s2));
        assertEquals(1, gameLogic.getWrongGuesses());

    }

}