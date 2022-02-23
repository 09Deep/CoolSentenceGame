package com.coolsentencegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        Button buttonToLevel1 = findViewById(R.id.buttontolevel1);
        buttonToLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLevelOne = new Intent(view.getContext(),LevelOneActivity.class);
                startActivity(toLevelOne);
            }
        });

        Button buttonToLevel2 = findViewById(R.id.buttontolevel2);
        buttonToLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLevelTwo = new Intent(view.getContext(),LevelTwoActivity.class);
                startActivity(toLevelTwo);
            }
        });

        Button buttonToLevel3 = findViewById(R.id.buttontolevel3);
        buttonToLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLevelThree = new Intent(view.getContext(),LevelThreeActivity.class);
                startActivity(toLevelThree);
            }
        });

    }
}