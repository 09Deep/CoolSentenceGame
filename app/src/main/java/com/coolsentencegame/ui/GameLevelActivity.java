package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coolsentencegame.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameLevelActivity extends AppCompatActivity {

    private Button gameSpeedEasy;
    private Button gameSpeedHard;
    private Button gameStart;
    FloatingActionButton backButtonToMainMenu;
    private EditText nRoundsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        gameSpeedEasy = findViewById(R.id.btn_easy);
        gameSpeedHard = findViewById(R.id.btn_hard);
        gameSpeedEasy.setEnabled(false);
        gameSpeedHard.setEnabled(true);

        // Game Speed Buttons
        gameSpeedEasy.setOnClickListener(this::onEasyBtnTap);
        gameSpeedHard.setOnClickListener(this::onHardBtnTap);

        // Back btn
        backButtonToMainMenu = findViewById(R.id.back_button_from_gamelevels);
        backButtonToMainMenu.setOnClickListener(this::onBackBtnTap);

        // Num rounds
        nRoundsInput = findViewById(R.id.input_num_rounds);
        nRoundsInput.setText("5");

        // Start Game btn
        gameStart = findViewById(R.id.btn_start);
        gameStart.setEnabled(true);
        gameStart.setOnClickListener(this::onStartBtnTap);

    }

    public void onEasyBtnTap(View view)
    {
        gameSpeedHard.setEnabled(true);
        gameSpeedEasy.setEnabled(false);
    }

    public void onHardBtnTap(View view)
    {
        gameSpeedEasy.setEnabled(true);
        gameSpeedHard.setEnabled(false);
    }

    public void onBackBtnTap(View view)
    {
        Intent toMainMenu = new Intent(view.getContext(), MainActivity.class);
        startActivity(toMainMenu);
    }

    public void onStartBtnTap(View view)
    {
        int nRounds = validateNumberInput();
        if(nRounds == -1) {
            nRoundsInput.setError("Please enter a number between 1 and 20");
            return;
        }

        Intent gameArgs = new Intent(GameLevelActivity.this, GameUI.class);
        gameArgs.putExtra("speed",get_speed(gameSpeedEasy, gameSpeedHard));
        gameArgs.putExtra("rounds", nRounds);
        startActivity(gameArgs);
    }

    private int validateNumberInput()
    {
        String text = nRoundsInput.getText().toString();
        if(text.isEmpty())
            return -1;

        int n = Integer.parseInt(text);
        if(n > 0 && n <= 20)
            return n;
        else
            return -1;
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