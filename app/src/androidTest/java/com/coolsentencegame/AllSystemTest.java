package com.coolsentencegame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DifficultyLevelTest.class,
        GameLoopTest.class,
        MenuNavTest.class,
        StatsActivityTest.class
})

public class AllSystemTest { }

