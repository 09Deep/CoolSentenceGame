package com.coolsentencegame.application;

import com.coolsentencegame.persistence.IScorePersistence;
import com.coolsentencegame.persistence.ISentencePersistence;
import com.coolsentencegame.persistence.ScorePersistence;
import com.coolsentencegame.persistence.SentencePersistence;

public class Services {
    private static ISentencePersistence sentencePersistence;
    private static IScorePersistence scorePersistence;

    public static synchronized ISentencePersistence getSentencePersistence() {
        if (sentencePersistence == null) {
            sentencePersistence = new SentencePersistence(Main.getDBPath());
//            sentencePersistence = new MockSentencePersistence();
        }

        return sentencePersistence;
    }

    public static synchronized IScorePersistence getScorePersistence() {
        if (scorePersistence == null) {
            scorePersistence = new ScorePersistence(Main.getDBPath());
//            scorePersistence = new MockScorePersistence();
        }

        return scorePersistence;
    }
}
