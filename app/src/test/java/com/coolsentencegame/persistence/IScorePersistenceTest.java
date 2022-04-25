package com.coolsentencegame.persistence;

import static org.junit.Assert.*;

import com.coolsentencegame.objects.Score;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class IScorePersistenceTest {

    @Test
    public void autoTest()
    {
        IScorePersistence scorePersistence = new MockScorePersistence();

        ArrayList<Score> scores = new ArrayList<Score>();
        ArrayList<Score> expected = new ArrayList<Score>();
        ArrayList<Score> retrieved;

        scores.add(new Score(1, 2));
        scores.add(new Score(3, 4));
        scores.add(new Score(5, 6));
        scores.add(new Score(7, 8));

        for(int i = 0; i < 4; i++) {
            expected.add(0, scores.get(i));
            scorePersistence.storeScore(scores.get(i));
            retrieved = scorePersistence.getPrevScores(i + 1);
            assertEquals(expected, retrieved);
        }

    }

}
