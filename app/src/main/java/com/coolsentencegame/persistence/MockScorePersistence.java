package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Score;

import java.util.ArrayList;

public class MockScorePersistence implements IScorePersistence {

    private final ArrayList<Score> scores;

    public MockScorePersistence()
    {
        scores = new ArrayList<Score>();
        scores.add(new Score (1, 2, "2022.03.31 23:02:16"));
        scores.add(new Score (3, 4, "2022.03.30 23:56:16"));
        scores.add(new Score (5, 6, "2022.03.29 23:22:16"));
    }

    public void storeScore(Score score)
    {
        scores.add(score);
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

    @Override
    public void removeScore(Score score)
    {
        scores.remove(score);
    }

    public Score getHighScore() {
        return null;
    }
}
