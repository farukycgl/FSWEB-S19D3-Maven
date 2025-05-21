package com.workintech.s19d2.controller;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<Account> getAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }

    @PostMapping
    public Account save(@RequestBody(required = false) Account account){
        if (account == null) {
            throw new AccessDeniedException("Forbidden");
        }
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable("id") Long id,
                          @RequestBody Account account){
        return accountService.getUpdateById(id, account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){

        accountService.getDeleteById(id);
    }
}
