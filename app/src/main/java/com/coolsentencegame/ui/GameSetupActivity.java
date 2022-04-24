package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.coolsentencegame.R;
import com.coolsentencegame.objects.GameParams;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameSetupActivity extends AppCompatActivity {

    FloatingActionButton backButtonToMainMenu;

    private RadioButton radioNormal;
    private RadioButton radioFast;

    private Button btnLevel1;
    private Button btnLevel2;
    private Button btnLevel3;
    private Button btnLevel4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Utils.getTheme());
        setContentView(R.layout.activity_game_setup);

        // Radio buttons
        radioNormal = findViewById(R.id.radioNormal);
        radioFast = findViewById(R.id.radioFast);
        radioNormal.toggle();

        // Back btn
        backButtonToMainMenu = findViewById(R.id.back_button_from_gamelevels);
        backButtonToMainMenu.setOnClickListener(this::onBackBtnTap);

        btnLevel1 = findViewById(R.id.btnLevel1);
        btnLevel2 = findViewById(R.id.btnLevel2);
        btnLevel3 = findViewById(R.id.btnLevel3);
        btnLevel4 = findViewById(R.id.btnLevel4);

        btnLevel1.setOnClickListener((View v) -> { toGame(1, 3); });
        btnLevel2.setOnClickListener((View v) -> { toGame(2, 4); });
        btnLevel3.setOnClickListener((View v) -> { toGame(3, 5); });
        btnLevel4.setOnClickListener((View v) -> { toGame(4, 6); });
    }

    public void onBackBtnTap(View view)
    {
        finish();
    }

    public void toGame(int min, int max)
    {
        GameParams gameParams;
        Intent gameArgs = new Intent(GameSetupActivity.this, GameActivity.class);

        if(radioNormal.isChecked())
            gameParams = new GameParams(GameParams.Speed.NORMAL, min, max);
        else
            gameParams = new GameParams(GameParams.Speed.FAST, min, max);

        System.out.println(gameParams);

        gameArgs.putExtra("params", gameParams);
        startActivity(gameArgs);
        finish();
    }
}