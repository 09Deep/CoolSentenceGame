package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coolsentencegame.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LevelTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        FloatingActionButton buttonToGameLevel = findViewById(R.id.back_button_from_leveltwo);
        buttonToGameLevel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent toGameLevels = new Intent(view.getContext(),GameLevelActivity.class);
                startActivity(toGameLevels);
            }
        });
    }
}