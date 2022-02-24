package com.coolsentencegame.objects;

public class Account
{
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
