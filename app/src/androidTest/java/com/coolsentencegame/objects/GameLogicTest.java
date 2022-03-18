package com.coolsentencegame.objects;

import static org.junit.Assert.*;

import com.coolsentencegame.logic.GameLogic;

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
    public void isPlayerSentenceCorrect() {
        GameLogic gameLogic = new GameLogic();
        Sentence sentence;

        gameLogic.newSentence();
        sentence = gameLogic.getSentence();

        //assertTrue(gameLogic.isPlayerSentenceCorrect(new ArrayList<String>(Arrays.asList(sentence.split(" ")))));
    }
}