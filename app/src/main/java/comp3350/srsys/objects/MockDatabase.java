package comp3350.srsys.objects;

import java.util.ArrayList;
import java.util.Random;

import comp3350.srsys.interfaces.IDatabase;

public class MockDatabase implements IDatabase {
    private Random random;

    private ArrayList<String> words;

    public MockDatabase() {
        random = new Random();
        words = new ArrayList<String>();
    }

    /*
    * FetchRandomWord
    *
    * Returns a string at a random index within the word database's bounds.
    *
     */
    public String FetchRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    /*
    * StoreScore
    *
    * Record the current score in the database.
     */
    public void StoreScore() {

    }
}
