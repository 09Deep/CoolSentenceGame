package com.coolsentencegame.objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sentence {
    @ColumnInfo(name = "sentence")
    public String sentence;

    @PrimaryKey
    public int ID;
}
