package com.coolsentencegame.objects;

public class Account
{
    final int DIFF_DEFAULT = 1;
    final String SPEED_DEFAULT = "SLOW";
    final String THEME_DEFAULT = "Default";
    final String[] VALID_SPEED = {"SLOW", "FAST"};
    final String[] VALID_THEME = {"Default", "Dark", "Light"};
    final int DIFF_MAX = 5;

    public int userID;

    public String firstName;

    public String lastName;



    private String theme;
    private int difficulty;
    private String gameSpeed;

    public void setTheme(String newTheme)
    {
        Boolean valid = false;
        for(int i = 0; i < VALID_THEME.length; i++)
        {
            if(newTheme.equals(VALID_THEME[i]))
            {
                valid = true;
            }
        }
        if(valid) {
            this.theme = newTheme;
        }
    }

    public String getTheme()
    {
        return this.theme;
    }

    public void setDifficulty(int diff)
    {
        Boolean valid = false;
        if(diff >= DIFF_DEFAULT && diff <= DIFF_MAX)
        {
            valid = true;
        }
        if(valid) {
            this.difficulty = diff;
        }
    }

    public int getDifficulty()
    {
        return this.difficulty;
    }

    public void setSpeed(String speed)
    {
        Boolean valid = false;
        for(int i = 0; i < VALID_SPEED.length; i++)
        {
            if(speed.equals(VALID_SPEED[i]))
            {
                valid = true;
            }
        }
        if(valid) {
            this.gameSpeed = speed;
        }
    }

    public String getSpeed()
    {
        return this.gameSpeed;
    }

    public Account()
    {

    }

}
