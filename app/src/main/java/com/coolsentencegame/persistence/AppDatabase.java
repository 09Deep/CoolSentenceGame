package com.coolsentencegame.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.coolsentencegame.interfaces.IAccountDao;
import com.coolsentencegame.interfaces.IAccountDataDao;
import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.interfaces.ISentenceDao;
import com.coolsentencegame.objects.Account;
import com.coolsentencegame.objects.AccountData;
import com.coolsentencegame.objects.Sentence;

@Database(entities = {AccountData.class, Sentence.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase implements IDatabase {
    public abstract IAccountDataDao accountDataDao();
    public abstract ISentenceDao sentenceDao();

    public Sentence FetchRandomSentence() {
        return sentenceDao().FetchRandomSentence();
    }

    public void StoreScore(int score) {

    }
}