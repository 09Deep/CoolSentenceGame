package com.coolsentencegame.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.coolsentencegame.R;
import com.coolsentencegame.application.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.coolsentencegame.R;

public class Utils {


    private static int sTheme = R.style.Theme_CoolSentenceGame;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_PINK = 1;
    public final static int THEME_GREEN = 2;
    public final static int THEME_BLUE = 3;
    public final static int THEME_RUBY = 4;
    public final static int THEME_PEACH = 5;


    public static void setTheme(int theme) {


        sTheme = theme;
    }


    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static int getTheme() {
        int id = 0;

        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                id = R.style.Theme_CoolSentenceGame;
                break;
            case THEME_BLUE:
                id = R.style.Theme_Blue;
                break;
            case THEME_GREEN:
                id = R.style.Theme_Green;
                break;
            case THEME_PINK:
                id = R.style.Theme_Pink;
                break;
            case THEME_RUBY:
                id = R.style.Theme_Ruby;
                break;
            case THEME_PEACH:
                id = R.style.Theme_Peach;
                break;
        }

        Log.i("theme id is : ", Integer.toString(id));
        return id;
    }

}