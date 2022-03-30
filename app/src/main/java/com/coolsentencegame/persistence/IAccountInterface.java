package com.coolsentencegame.persistence;

import com.coolsentencegame.objects.Account;

public interface IAccountInterface {
    Account getAccount(String name);

    Account insertAccount(final Account account);

    Account updateAccount(final Account account);

    void deleteAccount(final Account account);

}
