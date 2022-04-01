package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Score;

import java.util.ArrayList;

public interface IScorePersistence {

    void StoreScore(int correct, int wrong);

    // Get the last n scores, with the most recent score first.
    // Return all if n == 0
    ArrayList<Score> getPrevScores(int n);

}
