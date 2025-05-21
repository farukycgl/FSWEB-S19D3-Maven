package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id'li account bulunamadı."));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getUpdateById(Long id, Account updatedAccount) {

        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id'li account bulunamadı."));

        existingAccount.setName(updatedAccount.getName());

        return accountRepository.save(existingAccount);
    }

    @Override
    public void getDeleteById(Long id) {

        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id'li account bulunamadı."));

        accountRepository.deleteById(id);
    }
}
