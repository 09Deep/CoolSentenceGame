package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coolsentencegame.R;
import com.coolsentencegame.application.Services;

public class SettingsActivity extends AppCompatActivity {

    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Utils.getTheme());
        setContentView(R.layout.activity_settings);

        backBtn = findViewById(R.id.btn_settings_back);
        backBtn.setOnClickListener(this::onBackBtnTap);

        // HAHAHAHAHAHAHAHA
        // If you delete these lines, try
        // Go to settings, go back, go to stats or start a game
        // and the app will crash, because the database says.
        // we don't have the correct permissions.
        // I think this is because we are calling recreate()
        // in the main thread. When we do this, maybe we are lowering
        // permissions?
        Services.getScorePersistence();
        Services.getSentencePersistence();

        findViewById(R.id.btn_theme_pink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setTheme(Utils.THEME_PINK);
                finish();
                startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });

        findViewById(R.id.btn_theme_peach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setTheme(Utils.THEME_PEACH);
                finish();
                startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });

        findViewById(R.id.btn_theme_ruby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setTheme(Utils.THEME_RUBY);
                finish();
                startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });

        findViewById(R.id.btn_theme_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setTheme(Utils.THEME_DEFAULT);
                finish();
                startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });

        findViewById(R.id.btn_theme_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setTheme(Utils.THEME_GREEN);
                finish();
                startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });

        findViewById(R.id.btn_theme_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setTheme(Utils.THEME_BLUE);
                finish();
                startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });

    }

    public void onBackBtnTap(View view)
    {
        finish();
    }
}