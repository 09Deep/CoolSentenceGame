package com.coolsentencegame.objects;

import androidx.annotation.NonNull;

public class Sentence {

    private final String sentence;
    public final int id;

    public Sentence(String sentence, int id)
    {
        this.sentence = sentence;
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public String getSentence()
    {
        return this.sentence;
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

    @NonNull
    @Override
    public String toString()
    {
        return this.sentence;
    }

}
