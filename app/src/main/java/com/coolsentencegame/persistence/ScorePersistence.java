package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ScorePersistence implements IScorePersistence {

    private final String dbPath;

    public ScorePersistence(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Score fromResultSet(final ResultSet rs) throws SQLException {
        final int wrong = rs.getInt("CORRECT");
        final int correct = rs.getInt("WRONG");

        return new Score(correct, wrong);
    }

    @Override
    public void StoreScore(int correct, int wrong) {

    }

    @Override
    public ArrayList<Score> getPrevScores(int n) {
        ArrayList<Score> scores = new ArrayList<Score>();
        try {
            final Connection c = connection();
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM scores");

            while (rs.next()) {
//                final Score record = fromResultSet(rs);
//                scores.add(record);
                int s = rs.getInt("CORRECT");
                System.out.println(s);
            }

            rs.close();
            st.close();

            return scores;
        }
        catch (final SQLException e)
        {
//            throw new PersistenceException(e);
            return null;
        }

    }

    @Override
    public void removeScore(Score score) {

    }
}
