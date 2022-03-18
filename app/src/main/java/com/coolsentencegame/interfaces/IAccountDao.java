package com.coolsentencegame.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.coolsentencegame.objects.Account;

import java.util.List;

@Dao
public interface IAccountDao {
    @Query("SELECT * FROM account")
    List<Account> getAll();

    @Query("SELECT * FROM account WHERE userID IN (:userIds)")
    List<Account> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM account WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Account findByName(String first, String last);

    @Insert
    void insertAll(Account... accounts);

    @Delete
    void delete(Account accounts);
}
