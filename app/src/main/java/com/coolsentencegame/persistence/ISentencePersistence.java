package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Sentence;

public interface ISentencePersistence {

    Sentence getEasySentence();

    Sentence getHardSentence();

}
