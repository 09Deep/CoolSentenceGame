package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * AccountPersistence
 *
 * Implements account persistence with HSQLDB.
 */
public class AccountPersistence implements IAccountInterface {
    private final String dbPath;

    public AccountPersistence(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        //Jordan: not fully sure what certain parts of this mean. It's from the sample project.
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public Account getAccount(String name) {
        try (final Connection c = connection()) {

        } catch (final SQLException e) {

        }

        return null;
    }

    @Override
    public Account insertAccount(Account account) {
        try (final Connection c = connection()) {

        } catch (final SQLException e) {

        }

        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        try (final Connection c = connection()) {

        } catch (final SQLException e) {

        }

        return null;
    }

    @Override
    public void deleteAccount(Account account) {
        try (final Connection c = connection()) {

        } catch (final SQLException e) {

        }
    }
}
