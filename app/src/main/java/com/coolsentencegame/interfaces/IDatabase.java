package com.coolsentencegame.interfaces;

import com.coolsentencegame.objects.Sentence;

public interface IDatabase {
    Sentence FetchRandomSentence();

    void StoreScore(int score);
}
