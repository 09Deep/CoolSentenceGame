package com.coolsentencegame.integration;

public class GameLogicScoreTest {

//    @Test
//    public void automatedTest()
//    {
//        IScorePersistence scorePersistence = Services.getScorePersistence();
//        Random rand = new Random();
//        ArrayList<String> wrongTokens = new ArrayList<>();
//        int startSize = scorePersistence.getPrevScores(0).size();
//
//        for(int i = 0; i < 20; i++) {
//            int correct = 1 + rand.nextInt(10);
//            int wrong = 1 + rand.nextInt(10);
//            GameLogic gameLogic = new GameLogic(correct + wrong, Services.getScorePersistence());
//            ArrayList<String> tokens = gameLogic.getTokens();
//
//            // Make some right guesses
//            for(int j = 0; j < correct; j++) {
//                assertTrue(gameLogic.isPlayerSentenceCorrect(tokens));
//            }
//
//            // Make some wrong guesses
//            for(int j = 0; j < wrong; j++) {
//                assertFalse(gameLogic.isPlayerSentenceCorrect(wrongTokens));
//            }
//
//            // Check that it was added
//            ArrayList<Score> scores = scorePersistence.getPrevScores(1);
//            assertEquals(1, scores.size());
//            Score theScore = scores.get(0);
//            assertEquals(correct, theScore.getCorrect());
//            assertEquals(wrong, theScore.getWrong());
//            assertEquals(correct+wrong, theScore.getTotal());
//
//            // Cleanup
//            scorePersistence.removeScore(theScore);
//        }
//
//        int endSize = scorePersistence.getPrevScores(0).size();
//        assertEquals(startSize, endSize);
//
//    }

}
