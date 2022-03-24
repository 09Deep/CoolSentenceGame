package com.coolsentencegame.objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AccountData {
    @PrimaryKey
    public int userID;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "theme")
    public String theme;

    @ColumnInfo(name = "difficulty")
    public int difficulty;

    @ColumnInfo(name = "gameSpeed")
    public String gameSpeed;
}
