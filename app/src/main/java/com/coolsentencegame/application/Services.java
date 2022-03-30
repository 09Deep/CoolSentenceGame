package com.coolsentencegame.application;

import com.coolsentencegame.persistence.AccountPersistence;
import com.coolsentencegame.persistence.SentencePersistence;

public class Services {
    private static AccountPersistence accountPersistence;
    private static SentencePersistence sentencePersistence;

    public static synchronized AccountPersistence getAccountPersistence()
    {
        if (accountPersistence == null)
        {
            accountPersistence = new AccountPersistence(Main.getDBPath());
        }

        return accountPersistence;
    }

    public static synchronized SentencePersistence getSentencePersistence() {
        if (sentencePersistence == null) {
            // scPersistence = new SCPersistenceStub();
            sentencePersistence = new SentencePersistence(Main.getDBPath());
        }

        return sentencePersistence;
    }
}
