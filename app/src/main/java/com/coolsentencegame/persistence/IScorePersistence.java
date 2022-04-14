package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Score;

import java.util.ArrayList;

public interface IScorePersistence {

    void storeScore(Score score);

    // Get the last n scores, with the most recent score first.
    // Return all if n == 0
    ArrayList<Score> getPrevScores(int n);

    // Having trouble with IDENTITY primary key,
    // as for now, there is no way of deleting scores
    // since the scores table has no primary keu. Will do
    // in next iteration.
    void removeScore(Score score);

    Score getHighScore();
}
