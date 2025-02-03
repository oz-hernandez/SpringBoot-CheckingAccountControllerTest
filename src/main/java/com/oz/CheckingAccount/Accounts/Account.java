package com.oz.CheckingAccount.Accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    private Long id;
    private String name;
    private BigDecimal balance;

    public Account() {}
    public Account(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
}
