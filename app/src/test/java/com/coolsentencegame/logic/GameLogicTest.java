package com.coolsentencegame.logic;

import static org.junit.Assert.*;

import com.coolsentencegame.objects.Sentence;
import com.coolsentencegame.persistence.MockScorePersistence;
import com.coolsentencegame.persistence.MockSentencePersistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogicTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void newSentence() {
        GameLogic gameLogic = new GameLogic(5, new MockScorePersistence(), new MockSentencePersistence());
        Sentence sOne, sTwo;

        gameLogic.newSentence();
        sOne = gameLogic.getSentence();

        gameLogic.newSentence();
        sTwo = gameLogic.getSentence();

        assertNotEquals(sOne.toString(), sTwo.toString());
    }

    @Test
    public void isPlayerSentenceCorrect() {
        GameLogic gameLogic = new GameLogic(5, new MockScorePersistence(), new MockSentencePersistence());
        String sentence;

        gameLogic.newSentence();
        sentence = gameLogic.getSentence().toString();

        assertTrue(gameLogic.isPlayerSentenceCorrect(new ArrayList<String>(Arrays.asList(sentence.split(" ")))));
    }

    @Test
    public void getSentence() {
        GameLogic gameLogic = new GameLogic(5, new MockScorePersistence(), new MockSentencePersistence());

        gameLogic.newSentence();
        assertNotNull(gameLogic.getSentence());
    }

    @Test
    public void getTokens() {
        GameLogic gameLogic = new GameLogic(5, new MockScorePersistence(), new MockSentencePersistence());
        ArrayList<String> tokens;

        gameLogic.newSentence();
        tokens = gameLogic.getTokens();

        assertNotNull(tokens);
    }

    @Test
    public void getCorrectGuesses() {
        GameLogic gameLogic = new GameLogic(2, new MockScorePersistence(), new MockSentencePersistence());

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

        // Make a guess after game is done
        gameLogic.isPlayerSentenceCorrect(s2);
        assertEquals(1, gameLogic.getCorrectGuesses());
        assertEquals(1, gameLogic.getWrongGuesses());
    }

    @Test
    public void getWrongGuesses() {
        GameLogic gameLogic = new GameLogic(2, new MockScorePersistence(), new MockSentencePersistence());

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

        // Make a guess after game is done
        gameLogic.isPlayerSentenceCorrect(s2);
        assertEquals(1, gameLogic.getWrongGuesses());
        assertEquals(1, gameLogic.getCorrectGuesses());
    }

    @Test
    public void isDone()
    {
        GameLogic gameLogic = new GameLogic(5, new MockScorePersistence(), new MockSentencePersistence());
        ArrayList<String> tokens = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            gameLogic.isPlayerSentenceCorrect(tokens);
            assertEquals(i+1, gameLogic.getCurrentRoundNumber());
        }
        assertTrue(gameLogic.isDone());

        // Make a guess after we should be done
        gameLogic.isPlayerSentenceCorrect(tokens);
        assertTrue(gameLogic.isDone());
    }
}