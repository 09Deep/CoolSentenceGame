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

    @Override
    public boolean equals(Object other) {
        if(other instanceof Score) {
            Score other_score = (Score)other;
            boolean result = true;
            if (this.total != other_score.total) result = false;
            else if (this.correct != other_score.correct) result =  false;
            else if ( this.wrong != other_score.wrong ) result = false;
            return result;
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return date + " - " + correct + "/" + total + " (" + pcent + "%)";
    }
}
