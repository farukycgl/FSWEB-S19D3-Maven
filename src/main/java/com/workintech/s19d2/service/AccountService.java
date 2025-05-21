package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Account;

import java.util.AbstractCollection;
import java.util.List;
import java.util.Set;

public interface AccountService {
    List<Account> findAll();
    Account getAccountById(Long id);
    Account save(Account account);
    Account getUpdateById(Long id, Account updatedAccount);
    void getDeleteById(Long id);

}
