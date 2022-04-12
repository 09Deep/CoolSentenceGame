package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Sentence;

import java.util.ArrayList;
import java.util.Random;

/*
 * MockDatabase
 *
 * Implements the database interface using an arraylist.
 */
public class MockSentencePersistence implements ISentencePersistence {

    private Random random;
    private final ArrayList<Sentence> sentences;

    public MockSentencePersistence() {
        random = new Random();

        //initialize phrases
        sentences = new ArrayList<Sentence>();

        // 2
        sentences.add(new Sentence("Another one", 1));
        sentences.add(new Sentence("Easy peazy", 2));

        // 3
        sentences.add(new Sentence("Why hello there", 3));
        sentences.add(new Sentence("Three word sentence", 4));

        // 4
        sentences.add(new Sentence("This is a sentence", 5));
        sentences.add(new Sentence("A four word sentence", 6));

        // 5
        sentences.add(new Sentence("Dont have a cow man", 7));
        sentences.add(new Sentence("This a five word sentence", 8));

        // 6
        sentences.add(new Sentence("This is a harder sentence I guess", 9));
        sentences.add(new Sentence("This is a six word sentence", 10));

        // 7
        sentences.add(new Sentence("This is a harder sentence I suppose", 11));
        sentences.add(new Sentence("This is a much harder sentence I truly guess", 12));
        sentences.add(new Sentence("This is a something of a sentence I guess", 13));
        sentences.add(new Sentence("This is a longer sentence with some words", 14));

    }

    @Override
    public Sentence getSentence(int minLen, int maxLen) {
        int r = random.nextInt(sentences.size());
        Sentence s = sentences.get(r);
        while(s.getnTokens() < minLen || s.getnTokens() > maxLen) {
            r = random.nextInt(sentences.size());
            s = sentences.get(r);
        }
        return s;
    }
}
