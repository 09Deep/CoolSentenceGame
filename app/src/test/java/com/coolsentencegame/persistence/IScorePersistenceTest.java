package com.coolsentencegame.persistence;

import static org.junit.Assert.*;

import com.coolsentencegame.objects.Score;

import org.junit.Test;

import java.util.ArrayList;

public class IScorePersistenceTest {

    @Test
    public void autoTest()
    {
        IScorePersistence scorePersistence = new MockScorePersistence();
        ArrayList<Score> scores = new ArrayList<Score>();
        scores.add(new Score(1, 2));
        scores.add(new Score(3, 4));
        scores.add(new Score(5, 6));
        scores.add(new Score(7, 8));

        // Add in reverse order
        // Get prev returns list with most recent entries first
        scorePersistence.storeScore(scores.get(3));
        scorePersistence.storeScore(scores.get(2));
        scorePersistence.storeScore(scores.get(1));
        scorePersistence.storeScore(scores.get(0));

        ArrayList<Score> retrieved = scorePersistence.getPrevScores(scores.size());
        assertEquals(scores, retrieved);

        int n = scores.size();
        for(int i = 1; i < n; i++) {
            scorePersistence.removeScore(scores.get(0));
            scores.remove(0);
            retrieved = scorePersistence.getPrevScores(scores.size());
            assertEquals(scores, retrieved);
        }

    }

}
