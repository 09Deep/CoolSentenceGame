package com.coolsentencegame.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.coolsentencegame.interfaces.IAccountDao;
import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.interfaces.ISentenceDao;
import com.coolsentencegame.objects.Account;
import com.coolsentencegame.objects.Sentence;

@Database(entities = {Account.class, Sentence.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase implements IDatabase {
    public abstract IAccountDao accountDao();
    public abstract ISentenceDao sentenceDao();

    public Sentence FetchRandomSentence() {
        return sentenceDao().FetchRandomSentence();
    }

    public void StoreScore(int score) {

    }
}