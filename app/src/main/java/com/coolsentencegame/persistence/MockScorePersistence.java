package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Score;

import java.util.ArrayList;

public class MockScorePersistence implements IScorePersistence {

    private final ArrayList<Score> scores;

    public MockScorePersistence()
    {
        scores = new ArrayList<Score>();
        scores.add(new Score(5, 6));
        scores.add(new Score(9, 3));
        scores.add(new Score(1, 0));
        scores.add(new Score(4, 1));
    }

    public void StoreScore(int correct, int wrong) {
        scores.add((new Score(correct, wrong)));
    }

    // Get the last n scores, with the most recent score first.
    // Return all if n == 0
    @Override
    public ArrayList<Score> getPrevScores(int n) {
        ArrayList<Score> lastScores = new ArrayList<>();
        int size = scores.size();

        if(n > 0)
            n = Math.min(n, size);
        else
            n = size;

        for (int i = 0; i < n; i++) {
            lastScores.add(scores.get(size - 1 - i));
        }

        return lastScores;
    }

}
