package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coolsentencegame.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttontoGameLevels = findViewById(R.id.gamebutton);
        buttontoGameLevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(),GameLevelActivity.class);
                startActivity(i);
            }
        });

        Button buttontoStats = findViewById(R.id.statsbutton);
        buttontoStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),StatsActivity.class);

                i.putExtra("PTime",getPreviousTime());
                i.putExtra("CTime",getCurrentTime());

                startActivity(i);
            }
        });
    }

    public String getPreviousTime(){
        Bundle extras = getIntent().getExtras();
        String value = null;
        if (extras != null) {
            value = extras.getString("PTime");
            //The key argument here must match that used in the other activity
        }
        return value;
    }

    public String getCurrentTime(){
        Bundle extras = getIntent().getExtras();
        String value = null;
        if (extras != null) {
            value = extras.getString("CTime");
            //The key argument here must match that used in the other activity
        }
        return value;
    }



}
