package com.coolsentencegame.objects;

/*
 * Sentence
 *
 * Data structure that holds a sentence as an array of strings
 * and compares it to a player input sentence to test if they
 * remembered the sequence of words correctly.
 */
public class Sentence {
    private String[] correctSentence;
    private String[] playerSentence;

    private int sentenceLength;

    public Sentence(String s) {
        correctSentence = s.split(" ");
        sentenceLength = correctSentence.length;

        playerSentence = new String[sentenceLength];
    }

    /*
     * isPlayerSentenceCorrect
     *
     * Returns a boolean that states whether a user input sentence is correct or not.
     */
    public boolean isPlayerSentenceCorrect() {
        boolean correct = true; //only say there's a mistake when we find one.

        //exit the for loop if correct is no longer true.
        for(int i = 0; i < sentenceLength && correct; i++) {
            //check if the words at i are the same
            correct = correctSentence[i].compareTo(playerSentence[i]) == 0;
        }

        return correct;
    }
}
