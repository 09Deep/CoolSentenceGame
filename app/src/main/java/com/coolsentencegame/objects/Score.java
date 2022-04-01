package com.coolsentencegame.objects;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {

    private final int correct;
    private final int wrong;
    private final int total;
    private final float pcent;
    private final String date;
    // We will eventually add a time metric

    public Score(int correct, int wrong) {
        this.correct = correct;
        this.wrong = wrong;
        this.total = correct + wrong;
        this.pcent = ((float) correct / (float) total) * 100;

        @SuppressLint
                ("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        this.date = dateFormat.format(new Date());
    }

    public Score(int correct, int wrong, String date) {
        this.correct = correct;
        this.wrong = wrong;
        this.total = correct + wrong;
        this.pcent = ((float) correct / (float) total) * 100;
        this.date = date;
    }

    public int getCorrect() {
        return correct;
    }

    public int getWrong() {
        return wrong;
    }

    public int getTotal() {
        return total;
    }

    public float getPcent() {
        return pcent;
    }

    public String getDate() {
        return date;
    }

    public boolean isEqual( Score b) {
        boolean result = true;
        if (this.getTotal() != b.getTotal()) result = false;
        else if (this.getCorrect() != b.getCorrect()) result =  false;
        else if ( this.getWrong() != b.getWrong() ) result = false;
        return result;

    }

    @NonNull
    @Override
    public String toString() {
        return date + " - " + correct + "/" + total + " (" + pcent + "%)";
    }
}
