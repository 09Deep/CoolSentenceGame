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
    private final int cutoff = 5;

    public MockSentencePersistence() {
        random = new Random();

        //initialize phrases
        sentences = new ArrayList<Sentence>();

        sentences.add(new Sentence("Why hello there", 1));
        sentences.add(new Sentence("Another one", 2));
        sentences.add(new Sentence("This is a sentence", 3));
        sentences.add(new Sentence("Easy peazy", 4));
        sentences.add(new Sentence("Dont have a cow man", 5));
        sentences.add(new Sentence("This is a harder sentence I guess", 6));
        sentences.add(new Sentence("This is a harder sentence I suppose", 7));
        sentences.add(new Sentence("This is a much harder sentence I truly guess", 8));
        sentences.add(new Sentence("This is a something of a sentence I guess", 9));
        sentences.add(new Sentence("This is a longer sentence with some words", 10));

    }

    @Override
    public Sentence getEasySentence() {
        int r = random.nextInt(sentences.size());
        Sentence s = sentences.get(r);
        while(s.getnTokens() > 5) {
            r = random.nextInt(sentences.size());
            s = sentences.get(r);
        }
        return s;
    }

    @Override
    public Sentence getHardSentence() {
        int r = random.nextInt(sentences.size());
        Sentence s = sentences.get(r);
        while(s.getnTokens() <= cutoff) {
            r = random.nextInt(sentences.size());
            s = sentences.get(r);
        }
        return s;
    }

    @Override
    public int getEasyHardCutoff()
    {
        return cutoff;
    }
}
