package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coolsentencegame.R;

public class GameSummary extends AppCompatActivity {

    private Button btnHome;
    private Button btnAgain;
    private TextView textScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Utils.getTheme());
        setContentView(R.layout.activity_game_summary);

        Intent intent = getIntent();
        String msg = (String) intent.getSerializableExtra("msg");

        btnHome = findViewById(R.id.gs_btnHome);
        btnAgain = findViewById(R.id.gs_btnAgain);
        textScore = findViewById(R.id.gs_textScore);

        textScore.setText(msg);

        btnHome.setOnClickListener(this::onHomeBtnClick);
        btnAgain.setOnClickListener(this::onPlayAgainClick);
    }

    public void onHomeBtnClick(View v)
    {
        Intent intent = new Intent(GameSummary.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onPlayAgainClick(View v)
    {
        Intent intent = new Intent(GameSummary.this, GameSetupActivity.class);
        startActivity(intent);
        finish();
    }
}