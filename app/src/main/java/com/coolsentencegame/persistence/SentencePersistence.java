package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Sentence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * SentencePersistence
 *
 * Implements sentence persistence with HSQLDB.
 */
public class SentencePersistence implements ISentencePersistence {
    private final String dbPath;

    public SentencePersistence(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        //Jordan: not fully sure what certain parts of this mean. It's from the sample project.
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Sentence fromResultSet(final ResultSet rs) throws SQLException {
        final String sentence = rs.getString("sentence");
        final String id = rs.getString("id");

        return new Sentence(sentence, 0);
    }

    public List<Sentence> getStudentSequential() {
        final List<Sentence> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                final Sentence student = fromResultSet(rs);
                students.add(student);
            }
            rs.close();
            st.close();

            return students;
        } catch (final SQLException e) {
            return null;
        }
    }

    @Override
    public Sentence getRandomSentence() {
        Sentence sentence = null;

        try (final Connection c = connection()) {

        } catch (final SQLException e) {

        }

        return sentence;
    }

    @Override
    public Sentence insertSentence(Sentence sentence) {
        Sentence sentenceVar = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO sentences VALUES(?, ?)");
            //st.setString(1, sentence.getID());
            st.setString(2, sentence.getSentence());
            st.executeUpdate();
            sentenceVar = sentence;
        } catch (final SQLException e) {

        }

        return sentenceVar;
    }

    @Override
    public Sentence updateSentence(Sentence sentence) {
        Sentence sentenceVar = null;

        try (final Connection c = connection()) {

        } catch (final SQLException e) {

        }

        return sentenceVar;
    }

    @Override
    public void deleteSentence(Sentence sentence) {

    }
}
