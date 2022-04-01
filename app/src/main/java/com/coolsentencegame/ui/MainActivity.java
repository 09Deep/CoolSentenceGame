package com.coolsentencegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coolsentencegame.R;
import com.coolsentencegame.application.Main;
import com.coolsentencegame.application.Services;
import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.objects.Sentence;
import com.coolsentencegame.persistence.SentencePersistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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

        initializeDatabase();
        copyDatabaseToDevice();
    }

    private void initializeDatabase() {
        SentencePersistence sentencePersistence = Services.getSentencePersistence();

        List<Sentence> sentences = new ArrayList<Sentence>();
        sentences.add(new Sentence("Why hello there", "1"));
        sentences.add(new Sentence("That is based", "2"));
        sentences.add(new Sentence("Don't have a cow man", "3"));
        sentences.add(new Sentence("Cringe bro", "4"));
        sentences.add(new Sentence("Of and words and such", "5"));
        sentences.add(new Sentence("Our table is broken", "6"));
        sentences.add(new Sentence("Very groovy", "7"));

        Sentence sentenceTest = sentencePersistence.insertSentence(sentences.get(0));
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPath(dataDirectory.toString() + "/" + Main.getDBPath());

        } catch (final IOException ioe) {

        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
