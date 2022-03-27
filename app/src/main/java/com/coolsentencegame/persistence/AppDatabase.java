package com.coolsentencegame.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.coolsentencegame.interfaces.IAccountDataDao;
import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.interfaces.ISentenceDao;
import com.coolsentencegame.objects.AccountData;
import com.coolsentencegame.objects.Sentence;

import java.util.List;
import java.util.Random;

@Database(entities = {AccountData.class, Sentence.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase implements IDatabase {
    final String sentence = "How are you";

    public abstract IAccountDataDao accountDataDao();
    public abstract ISentenceDao sentenceDao();

    private Random random;

    public AppDatabase() {
        random = new Random();

        FillSentenceDatabase();
    }

    public void FillSentenceDatabase() {
        sentenceDao().insertAll(new Sentence(sentence));
    }

    public Sentence FetchRandomSentence() {
        List<Sentence> sentenceList = sentenceDao().getAll();

        return sentenceList.get(random.nextInt(sentenceList.size()));
    }

    public void StoreScore(int score) {

    }
}