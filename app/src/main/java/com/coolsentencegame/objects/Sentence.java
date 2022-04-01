package com.coolsentencegame.objects;

import androidx.annotation.NonNull;

public class Sentence {

    private final String sentence;
    public final String id;

    public Sentence(String sentence, String id)
    {
        this.sentence = sentence;
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public String getSentence()
    {
        return this.sentence;
    }

    public String getID() {
        return id;
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
