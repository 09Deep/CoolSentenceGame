package com.coolsentencegame.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.coolsentencegame.objects.Sentence;

import java.util.List;

@Dao
public interface ISentenceDao {
    @Query("SELECT * FROM sentence")
    List<Sentence> getAll();

    @Query("SELECT * FROM sentence WHERE ID IN (:sentenceIds)")
    List<Sentence> loadAllByIds(int[] sentenceIds);

    Sentence FetchRandomSentence();

    @Insert
    void insertAll(Sentence... sentences);

    @Delete
    void delete(Sentence sentence);
}
