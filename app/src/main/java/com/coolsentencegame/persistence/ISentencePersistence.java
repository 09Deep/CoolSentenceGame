package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Sentence;

public interface ISentencePersistence {
    Sentence getRandomSentence();

    Sentence insertSentence(Sentence sentence);

    Sentence updateSentence(Sentence sentence);

    void deleteSentence(Sentence sentence);
}
