package com.coolsentencegame.interfaces;

public interface IDatabase {
    String FetchRandomWord();

    void StoreScore(int score);

    void storeTime(double time);

    double getPreviousTime();

    double getCurrentTime();
}
