package com.coolsentencegame.objects;

import com.coolsentencegame.interfaces.ISentenceDao;

import java.util.List;

public class SentenceDao implements ISentenceDao {

    @Override
    public List<Sentence> getAll() {
        return null;
    }

    @Override
    public List<Sentence> loadAllByIds(int[] sentenceIds) {
        return null;
    }

    @Override
    public Sentence FetchRandomSentence() {
        return null;
    }

    @Override
    public void insertAll(Sentence... sentences) {

    }

    @Override
    public void delete(Sentence sentence) {

    }
}
