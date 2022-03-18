package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coolsentencegame.R;
import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.persistence.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private IDatabase database;

    public IDatabase GetDatabase() {
        if(database == null) {
            database = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").build();
        }

        return database;
    }



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
    }
}
