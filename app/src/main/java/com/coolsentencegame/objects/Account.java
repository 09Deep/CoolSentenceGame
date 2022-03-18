package com.coolsentencegame.objects;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account
{
    @PrimaryKey
    public int userID;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "last_name")
    private Preferences accPref;

    public Account()
    {
        this.accPref = new Preferences();
    }


    public void setTheme(String newTheme)
    {
        this.accPref.setTheme(newTheme);
    }

    public void setSpeed(String newSpeed)
    {
        this.accPref.setSpeed(newSpeed);
    }

    public void setDifficulty(int newDiff)
    {
        this.accPref.setDifficulty(newDiff);
    }
}
