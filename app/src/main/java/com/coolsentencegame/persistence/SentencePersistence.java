package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Sentence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
