package com.coolsentencegame.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.coolsentencegame.objects.AccountData;

import java.util.List;

@Dao
public interface IAccountDataDao {
    @Query("SELECT * FROM accountData")
    List<AccountData> getAll();

    @Query("SELECT * FROM accountData WHERE userID IN (:userIds)")
    List<AccountData> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM accountData WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    AccountData findByName(String first, String last);

    @Insert
    void insertAll(AccountData... accounts);

    @Delete
    void delete(AccountData accounts);
}
