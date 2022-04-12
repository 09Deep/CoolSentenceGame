package com.coolsentencegame.objects;

import java.io.Serializable;

public class GameParams implements Serializable {

    private final Speed speed;
    private final int minSentenceLen;
    private final int maxSentenceLen;

    public enum Speed {
        NORMAL,
        FAST
    }

    public GameParams(Speed speed, int minLen, int maxLen)
    {
        this.speed = speed;
        this.minSentenceLen = minLen;
        this.maxSentenceLen = maxLen;
    }

    public Speed getSpeed()
    {
        return speed;
    }

    public int getMinLen() {
        return minSentenceLen;
    }

    public int getMaxLen() {
        return maxSentenceLen;
    }

    @Override
    public String toString()
    {
        return "Speed: " + speed + " | [" + minSentenceLen + ", " + maxSentenceLen + "]";
    }

}
