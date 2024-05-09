package com.example.BankSystem.controller;

import com.example.BankSystem.model.Account;
import com.example.BankSystem.model.Transaction;
import com.example.BankSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestParam String accountId, @RequestParam double amount) {
        accountService.depositMoney(accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawMoney(@RequestParam String accountId, @RequestParam double amount) {
        accountService.withdrawMoney(accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable String accountId) {
        double balance = accountService.getAccountBalance(accountId);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<?> getAccountTransactions(@PathVariable String accountId) {
        return new ResponseEntity<>(accountService.getAccountTransactions(accountId), HttpStatus.OK);
    }
}
