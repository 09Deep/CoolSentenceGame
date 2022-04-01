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
        final int id = rs.getInt("id");

        return new Sentence(sentence, id);
    }

    @Override
    public Sentence getEasySentence() {
        return null;
    }

    @Override
    public Sentence getHardSentence() {
        return null;
    }
}
