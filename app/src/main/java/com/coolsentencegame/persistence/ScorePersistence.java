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

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Score fromResultSet(final ResultSet rs) throws SQLException
    {
        final int correct = rs.getInt("CORRECT");
        final int wrong = rs.getInt("WRONG");
        final String date = rs.getString("SCORETIME");
        return new Score(correct, wrong, date);
    }

    public Score getHighScore() {
        ArrayList<Score> prevScores = getPrevScores(0);
        Score currScore = null;
        Score currHighestScore = null;
        int currScoreEvaluation = 0;
        int highestScoreEvaluation = Integer.MIN_VALUE;

        //find the highest score.
        for(int i = 0; i < prevScores.size(); i++) {
            currScore = prevScores.get(i);

            if((currScoreEvaluation = evaluateScore(currScore)) > highestScoreEvaluation) {
                highestScoreEvaluation = currScoreEvaluation;
                currHighestScore = currScore;
            }
        }

        return currHighestScore;
    }

    public int evaluateScore(Score score) {
        return score.getCorrect() - score.getWrong();
    }

    @Override
    public void storeScore(Score score)
    {
        try(final Connection c = connection()) {
            final PreparedStatement ps = c.prepareStatement("INSERT INTO scores VALUES(?, ?, ?)");
            ps.setInt(1, score.getCorrect());
            ps.setInt(2, score.getWrong());
            ps.setString(3, score.getDate());
            ps.executeUpdate();
            ps.close();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public ArrayList<Score> getPrevScores(int n) {
        String limit = "";
        if(n > 0)
            limit = " limit " + n;

        ArrayList<Score> scores = new ArrayList<Score>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM scores ORDER BY SCORETIME DESC" + limit);

            while (rs.next()) {
                final Score record = fromResultSet(rs);
                scores.add(record);
            }

            rs.close();
            st.close();

            return scores;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }

    }

    @Override
    public void removeScore(Score score)
    {

    }
}
