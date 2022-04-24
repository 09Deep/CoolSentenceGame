package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import com.coolsentencegame.R;
import com.coolsentencegame.application.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SettingActivity extends AppCompatActivity{
    private Button btnBack;
    private AppCompatActivity setting_activiy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.mainActivity.finish();
        setTheme(Utils.getTheme());
        setContentView(R.layout.sample_setting_activity);
setting_activiy = this;

        btnBack = findViewById(R.id.btnBack2);
        btnBack.setOnClickListener(this::onBackBtnTap);

        findViewById(R.id.btn_theme_pink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Utils.settingTheme(Utils.THEME_PINK);
           setting_activiy.finish();
           setting_activiy.startActivity(new Intent(setting_activiy,setting_activiy.getClass()));


            }
        });

        findViewById(R.id.btn_theme_peach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.settingTheme(Utils.THEME_PEACH);
                setting_activiy.finish();
                setting_activiy.startActivity(new Intent(setting_activiy,setting_activiy.getClass()));

            }
        });
        findViewById(R.id.btn_theme_ruby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.settingTheme(Utils.THEME_RUBY);
                setting_activiy.finish();
                setting_activiy.startActivity(new Intent(setting_activiy,setting_activiy.getClass()));

            }
        });
        findViewById(R.id.btn_theme_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.settingTheme(Utils.THEME_DEFAULT);
                setting_activiy.finish();
                setting_activiy.startActivity(new Intent(setting_activiy,setting_activiy.getClass()));

            }
        });
        findViewById(R.id.btn_theme_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.settingTheme(Utils.THEME_GREEN);
                setting_activiy.finish();
                setting_activiy.startActivity(new Intent(setting_activiy,setting_activiy.getClass()));

            }
        });
        findViewById(R.id.btn_theme_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.settingTheme(Utils.THEME_BLUE);
                setting_activiy.finish();
                setting_activiy.startActivity(new Intent(setting_activiy,setting_activiy.getClass()));

            }
        });
    }

    public void onBackBtnTap(View view)
    {
        finish();
        Intent i = new Intent(view.getContext(),MainActivity.class);
        startActivity(i);
    }
}