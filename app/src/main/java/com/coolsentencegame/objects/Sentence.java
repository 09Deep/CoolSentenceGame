package com.coolsentencegame.objects;

import androidx.annotation.NonNull;

public class Sentence {

    private final String sentence;
    private final int id;
    private final int nTokens;

    public Sentence(String sentence, int id)
    {
        this.sentence = sentence;
        this.id = id;
        this.nTokens = sentence.split(" ").length;
    }

    public int getId()
    {
        return this.id;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Sentence) {
            Sentence otherSent = (Sentence)other;
            return this.id == otherSent.getId();
        }
        else {
            return false;
        }
    }

    public int getnTokens() {
        return nTokens;
    }

    @NonNull
    @Override
    public String toString()
    {
        return this.sentence;
    }

}
