package com.oz.CheckingAccount.Controllers;

import com.oz.CheckingAccount.Accounts.Account;
import com.oz.CheckingAccount.Accounts.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountRepository accountRepository;

    private AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/{name}")
    private ResponseEntity<Account> getAccountByName(@PathVariable String name) {
        Account account = accountRepository.findByName(name);
        if(account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
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
