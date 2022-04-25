package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Score;
import com.coolsentencegame.objects.Sentence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * SentencePersistence
 *
 * Implements sentence persistence with HSQLDB.
 */
public class SentencePersistence implements ISentencePersistence {
    private final String dbPath;
    private final ArrayList<Sentence> sentenceCache;
    private int minCacheLen;
    private int maxCacheLen;

    public SentencePersistence(final String dbPath)
    {
        this.dbPath = dbPath;

        sentenceCache = new ArrayList<Sentence>();
        minCacheLen = -1;
        maxCacheLen = -1;
    }

    private Connection connection() throws SQLException {
        //Jordan: not fully sure what certain parts of this mean. It's from the sample project.
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Sentence fromResultSet(final ResultSet rs) throws SQLException {
        final String sentence = rs.getString("sentence");
        final int id = rs.getInt("sid");
        return new Sentence(sentence, id);
    }

    private void fillSentenceCache(int min, int max)
    {
        sentenceCache.clear();
        try (final Connection c = connection()) {
            final String sql = "SELECT * FROM SENTENCES WHERE SENTENCES.LEN >= " + min + " AND SENTENCES.LEN <= " + max;
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                final Sentence sentence = fromResultSet(rs);
                sentenceCache.add(sentence);
            }

            rs.close();
            st.close();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    private boolean isCacheStale(int min, int max)
    {
        return sentenceCache.isEmpty() || (min != minCacheLen || max != maxCacheLen);
    }

    @Override
    public Sentence getSentence(int minLen, int maxLen) {
        if(isCacheStale(minLen, maxLen)) {
            System.out.println("STALE CACHE");
            fillSentenceCache(minLen, maxLen);
            minCacheLen = minLen;
            maxCacheLen = maxLen;
        }

        Random rand = new Random();
        int r = rand.nextInt(sentenceCache.size());
        return sentenceCache.get(r);
    }
}
