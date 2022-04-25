package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Sentence;

public interface ISentencePersistence {

    Sentence getSentence(int minLen, int maxLen);

}
