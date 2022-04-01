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
    private final int CUTOFF = 5;
    private final ArrayList<Sentence> easyCache;
    private final ArrayList<Sentence> hardCache;

    public SentencePersistence(final String dbPath)
    {
        this.dbPath = dbPath;
        easyCache = new ArrayList<Sentence>();
        hardCache = new ArrayList<Sentence>();
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

    private void fillCache()
    {
        // For now, just add everything into the cache, database
        // is small. We can refactor this to more intelligently use the
        // cache in iteration 3 when our db gets bigger
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM SENTENCES");

            while (rs.next()) {
                final Sentence sentence = fromResultSet(rs);
                if(sentence.getnTokens() <= CUTOFF)
                    easyCache.add(sentence);
                else
                    hardCache.add(sentence);
            }

            rs.close();
            st.close();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Sentence getEasySentence()
    {
        if(easyCache.isEmpty())
            fillCache();
        Random rand = new Random();
        int r = rand.nextInt(easyCache.size());
        return easyCache.get(r);
    }

    @Override
    public Sentence getHardSentence()
    {
        if(hardCache.isEmpty())
            fillCache();
        Random rand = new Random();
        int r = rand.nextInt(hardCache.size());
        return hardCache.get(r);
    }
}
