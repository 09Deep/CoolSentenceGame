package com.coolsentencegame.logic;

import static org.junit.Assert.*;

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
        GameLogic gameLogic = new GameLogic();
        String sOne, sTwo;

        gameLogic.newSentence();
        sOne = gameLogic.getSentence();

        gameLogic.newSentence();
        sTwo = gameLogic.getSentence();

        assertNotEquals(sOne, sTwo);
    }

    @Test
    public void isPlayerSentenceCorrect() {
        GameLogic gameLogic = new GameLogic();
        String sentence;

        gameLogic.newSentence();
        sentence = gameLogic.getSentence();

        assertTrue(gameLogic.isPlayerSentenceCorrect(new ArrayList<String>(Arrays.asList(sentence.split(" ")))));
    }

    @Test
    public void getSentence() {
        GameLogic gameLogic = new GameLogic();

        gameLogic.newSentence();
        assertNotNull(gameLogic.getSentence());
    }

    @Test
    public void getTokens() {
        GameLogic gameLogic = new GameLogic();
        ArrayList<String> tokens;

        gameLogic.newSentence();
        tokens = gameLogic.getTokens();

        assertNotNull(tokens);
    }

    @Test
    public void getTokensRandomized() {
        GameLogic gameLogic = new GameLogic();

        gameLogic.newSentence();

        assertNotEquals(gameLogic.getTokens(), gameLogic.getTokensRandomized());
    }

    @Test
    public void getCorrectGuesses() {
        GameLogic gameLogic = new GameLogic();

        assertEquals(0, gameLogic.getCorrectGuesses());
    }

    @Test
    public void getWrongGuesses() {
        GameLogic gameLogic = new GameLogic();

        assertEquals(0, gameLogic.getWrongGuesses());
    }
}