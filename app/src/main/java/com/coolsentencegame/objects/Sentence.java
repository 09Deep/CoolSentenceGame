package com.coolsentencegame.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sentence {
    private String sentence;

    @PrimaryKey
    private int ID;

    public String GetString() {
        return sentence;
    }

    public int GetID() {
        return ID;
    }
}
