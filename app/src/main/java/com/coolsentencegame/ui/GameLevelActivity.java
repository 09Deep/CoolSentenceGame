package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coolsentencegame.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        Button game_speed_easy = findViewById(R.id.game_speed_easy);
        Button game_speed_hard = findViewById(R.id.game_speed_hard);
        game_speed_easy.setEnabled(false);
        game_speed_hard.setEnabled(true);
        game_speed_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game_speed_hard.setEnabled(true);
                game_speed_easy.setEnabled(false);
            }
        });
        game_speed_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game_speed_easy.setEnabled(true);
                game_speed_hard.setEnabled(false);
            }
        });

        FloatingActionButton backbuttonToMainMenu = findViewById(R.id.back_button_from_gamelevels);
        backbuttonToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainMenu = new Intent(view.getContext(), MainActivity.class);
                startActivity(toMainMenu);
            }
        });

        Button buttonToLevel1 = findViewById(R.id.buttontolevel1);

        buttonToLevel1.setEnabled(true);

        buttonToLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent toLevelOne = new Intent(GameLevelActivity.this, GameUI.class);
                Intent toLevelOne= new Intent(view.getContext(), GameUI.class);
                //   toLevelOne.putExtra("speed",get_speed(game_speed_easy,game_speed_hard));
                startActivity(toLevelOne);
            }
        });

        Button buttonToLevel2 = findViewById(R.id.buttontolevel2);
        buttonToLevel2.setEnabled(true);
        buttonToLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLevelTwo = new Intent(view.getContext(), LevelTwoActivity.class);
                toLevelTwo.putExtra("speed",get_speed(game_speed_easy,game_speed_hard));
                startActivity(toLevelTwo);
            }
        });

        Button buttonToLevel3 = findViewById(R.id.buttontolevel3);
        buttonToLevel3.setEnabled(true);
        buttonToLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLevelThree = new Intent(view.getContext(), LevelThreeActivity.class);
                toLevelThree.putExtra("speed",get_speed(game_speed_easy,game_speed_hard));
                startActivity(toLevelThree);
            }
        });




    }

    private int get_speed(Button easy, Button hard) {
        int result = -1;
        if (easy.isEnabled() && !hard.isEnabled())
            result = 0;
        if (!easy.isEnabled() && hard.isEnabled())
            result = 1;
        return result;
    }
}