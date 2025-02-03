package com.oz.CheckingAccount.Controllers;

import com.oz.CheckingAccount.Accounts.Account;
import com.oz.CheckingAccount.Accounts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountRepository accountRepository;

    private AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    private ResponseEntity<Void> createAccount(@RequestBody Account account, UriComponentsBuilder ucb) {
        Account newAccount = accountRepository.save(account);
        URI location = ucb
                .path("accounts/{id}")
                .buildAndExpand(newAccount.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
