package com.coolsentencegame.persistence;

import static org.junit.Assert.*;

import com.coolsentencegame.application.Services;
import com.coolsentencegame.objects.Score;

import org.junit.Test;

import java.util.ArrayList;

public class IScorePersistenceTest {

    @Test
    public void test1() {


        int size = 4;
        ArrayList<Score> test_array = new ArrayList<>();

        test_array.add(new Score(2, 3));
        test_array.add(new Score(1, 3));
        test_array.add(new Score(4, 7));
        test_array.add(new Score(2, 9));

        IScorePersistence s_Persistence = new MockScorePersistence();

        s_Persistence.StoreScore(test_array.get(0));
        s_Persistence.StoreScore(test_array.get(1));
        s_Persistence.StoreScore(test_array.get(2));
        s_Persistence.StoreScore(test_array.get(3));


        ArrayList<Score> test_scores = s_Persistence.getPrevScores(size);
        assertEquals(test_scores.size(), test_array.size());

        for (int i = 0; i < size; i++) {
            assert (test_scores.get(i).isEqual(test_array.get(i)));
        }
        s_Persistence.removeScore(test_array.get(0));
        size--;
        test_scores = s_Persistence.getPrevScores(size);
        assertEquals(test_scores.size(), test_array.size());

        for (int i = 0; i < size; i++) {
            assert (test_scores.get(i).isEqual(test_array.get(i)));
        }

        s_Persistence.removeScore(test_array.get(0));
        size--;
        test_scores = s_Persistence.getPrevScores(size);
        assertEquals(test_scores.size(), test_array.size());

        for (int i = 0; i < size; i++) {
            assert (test_scores.get(i).isEqual(test_array.get(i)));
        }

        test_array.add(new Score(8, 2));
        test_array.add(new Score(9, 5));
    size +=2;

        test_scores = s_Persistence.getPrevScores(size);
        assertEquals(test_scores.size(), test_array.size());

        for (int i = 0; i < size; i++) {
            assert (test_scores.get(i).isEqual(test_array.get(i)));
        }


    }

}
