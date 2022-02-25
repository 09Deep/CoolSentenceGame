package com.coolsentencegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LevelOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        FloatingActionButton buttonToGameLevel = findViewById(R.id.back_button_from_levelone);
        buttonToGameLevel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent toGameLevels = new Intent(view.getContext(),GameLevelActivity.class);
                startActivity(toGameLevels);
            }
        });

    }
}