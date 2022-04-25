package com.coolsentencegame.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IScorePersistenceTest.class,
        ISentencePersistenceTest.class,
})


public class AllPersistenceTest { }