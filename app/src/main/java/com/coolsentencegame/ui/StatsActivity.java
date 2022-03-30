package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.coolsentencegame.R;
import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.logic.GameLogic;
import com.coolsentencegame.persistence.MockDatabase;

public class StatsActivity extends AppCompatActivity {
    TextView currTimeText;
    TextView prevTimeText;
    GameLogic logicOfGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logicOfGame = new GameLogic();
        setContentView(R.layout.activity_stats);

        prevTimeText = (TextView) findViewById(R.id.textView9);

        prevTimeText.setText(getPrevious());

        currTimeText = (TextView) findViewById(R.id.textView11);

        currTimeText.setText(getCurrent());
    }

    private String getPrevious(){
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("PTime");
        return value;
    }

    private String getCurrent(){
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("CTime");
        return value;
    }

}